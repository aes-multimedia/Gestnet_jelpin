/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

/**
 * This class contains the functionality for CRC. 
 * The class is a bundle of functions to handle
 * the CRC issues.
 */
public class CRCCalc {

	/**
	 * help crc register
	 */
	private int wCrcReg = 0xffff;

	/**
	 * CRC preset
	 * remarks:	Function must be called every time a new CRC has to be calculated.
	 * It initializes the CRC check sum.
	 */
	private void CrcPreset() {
		this.wCrcReg = 0xffff;
	}
	
	/**
	 * Reverse bits.
	 * remarks:	Part of the CRC calculation.
	 * @param in input 16 bits in form of a WORD value
	 * @return Reversed 16 bits of the input value
	 */
	private int reverse(int in)
	{
		int i, OverFlow = 0;

		for(i=0; i<16; i++)
		{
			OverFlow |= ((in & (int) 0x8000) >> (15-i));
			in <<= (int) 1;
			
			OverFlow &= 0xffff;
			in &= 0xffff;
		}

		return OverFlow;
	}
	
	/**
	 * ???
	 * remarks:	Part of the CRC calculation
	 * @param next_byte A byte of the sequence for that the CRC is generated
	 */
	private void CrcByteIn(byte next_byte)
	{
		short count;
		int feedback, Mask = 0;

		for (count = 0; count < 8; count++)
		{
			feedback = (this.wCrcReg >> (int) 15);
			feedback ^= (int) next_byte;
			feedback &= 1;

			Mask = feedback;
			Mask |= (feedback << (int) 12);
			Mask |= (feedback << (int) 5 );
			Mask &= 0xffff;

			this.wCrcReg <<= 1;
			this.wCrcReg &= 0xffff;
			this.wCrcReg ^= Mask;

			next_byte >>= 1;
		}
	}

	/**
	 * get CRC read
	 * remarks:	1. meaning: When a CRC has to be generated, the function returns the CRC
	 * (low-Byte: at pos n-1 in array to send; high-Byte: at pos n in array to send)
	 * 2. meaning: If a CRC shall be checked, after calculation the function
	 * returns 0 if the check succeeded.
	 * @return The CRC value stored in 2 bytes 
	 */
	public int CrcRead() {
		return this.wCrcReg;
	}
	
	/**
	 * Create CRC Rest.
	 * remarks: The functions expects that the byte sequence already includes the space for the
	 * 2 bytes for the CRC. The content of those 2 bytes are ignored. They become overridden by 
	 * this function. The 2nd parameter must represent the size of the frame, including the 2 bytes.
	 * The caller is responsible for the correctness of the passed pframe. There is
	 * no error checking within this function.
	 * @param pframe [in,out] A pointer to a byte sequence for which a CRC shall be calculated
	 * @param len  [in] The length of the byte sequence, includes the 2 bytes for the CRC
	 */
	public void createCRC(int[] pframe, final int len)
	{
		int i;
		int wCrc;

		//Validate pointer to byte sequence
		if(pframe == null)
		{
	      return;
		}

		//Start calculation
		CrcPreset();

		for(i = 0; i < len-2; i++)
			CrcByteIn((byte) pframe[i]);

		wCrc = reverse(this.wCrcReg);

		//Calculation done -> write result into the last 2 bytes of the frame
		pframe[len-2] = wCrc % 256; //Lo-Byte
		pframe[len-1] = wCrc / 256; //Hi-Byte
	}

	/**
	 * Validate CRC.
	 * remarks: This function allows to validate an received byte-array (frame) that was
	 * transmitted by a device.
	 * @param pframe [in,out] A pointer to a byte sequence for which a CRC shall be validated
	 * @param len [in] The total length of the byte sequence
	 * @return True if validation succeeded, otherwise false
	 */
	public boolean validateCRC(char[] pframe, final int len)
	{
		int i;

		//Validate pointer to byte sequence
		if(pframe == null)
		{
	      return false;
		}

		CrcPreset();

		for(i = 0; i < len; i++)
			CrcByteIn((byte) pframe[i]);

		if(CrcRead() == 0) 
			return true;
		else
			return false;
	}

	
}

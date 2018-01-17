/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums;

import android.util.Log;

import java.util.Locale;

/**
 * Idents definition
 * @author sergey.zaburunov
 */
public enum Idents {
// 		                    0    1           2                   3                   4                   5                   6                   7                   8                   9                   10                  11
// 		                    ID,  ID_old,     DE,                 EN,                 FR,                 ES,                 IT,                 NL,                 SV,                 CS,                 HU,                 DA
	ident_0x00000100(0x00000100, 0x00000100, "T1"               ,"T1"               ,"T1"               ,"T1"               ,"T1"               ,"T1"               ,"T1"               ,"T1"               ,"T1"               ,"T1"               ),
	ident_0x00000101(0x00000101, 0x00000101, "AT"               ,"FT"               ,"TF"               ,"TH"               ,"TF"               ,"Tr"               ,"AT"               ,"TS"               ,"Tfg�z"            ,"RT"               ),
	ident_0x00000102(0x00000102, 0x00000102, "VT"               ,"AT"               ,"TA"               ,"TA"               ,"TA"               ,"Tv"               ,"VT"               ,"TV"               ,"Tlev."            ,"AT"               ),
	ident_0x00000103(0x00000103, 0x00000103, "GT"               ,"Itemp"            ,"Tapp"             ,"TI"               ,"TL"               ,"Ti"               ,"DT"               ,"Tpr�str"          ,"Tg�z"             ,"Itemp"            ),
	ident_0x00000109(0x00000109, 0x00000109, "ATP"              ,"Dew Pt"           ,"Td ac"            ,"td"               ,"tdp"              ,"ADP"              ,"DP"               ,"RB"               ,"Harmp"            ,"DP"               ),
	ident_0x00000113(0x00000113, 0x00000113, "HotSp"            ,"Hotsp"            ,"Fcen"             ,"HotSp"            ,"CenFl"            ,"HotSp"            ,"HotSp"            ,"J�dro"            ,"HotSp"            ,"HotSpot"          ),
	ident_0x00000122(0x00000122, 0x00000122, "E-Temp"           ,"E-Temp"           ,"Temp-E"           ,"Temp. E"          ,"E-Temp"           ,"E-Temp"           ,""                 ,"E-Tep"            ,"E-hom."           ,"E-Temp."          ),
	ident_0x00000124(0x00000124, 0x00000124, "AT�"              ,"AT�"              ,"TF�"              ,"AT�"              ,"TF�"              ,"AT�"              ,""                 ,"TS�"              ,"AT�"              ,"OT�"              ),
	ident_0x00000125(0x00000125, 0x00000125, "VT�"              ,"VT�"              ,"TA�"              ,"VT�"              ,"TA�"              ,"VT�"              ,""                 ,"TV�"              ,"VT�"              ,"FT�"              ),
	ident_0x00000301(0x00000301, 0x00000301, "Zug"              ,"Draught"          ,"Tir."             ,"Tiro"             ,"Tirag."           ,"Trek"             ,"Drag"             ,"tah"              ,"Huzat"            ,"Tr�k"             ),
	ident_0x00000302(0x00000302, 0x00000302, "Deltap"           ,"Deltap"           ,"Deltap"           ,"Deltap"           ,"Press"            ,"DeltaP"           ,"Deltap"           ,"Deltap"           ,"DeltaP"           ,"Deltap"           ),
	ident_0x00000303(0x00000303, 0x00000303, "Pabs"             ,"Pabs"             ,"Pabs"             ,"Pabs"             ,"P ass"            ,"Pabs"             ,"Pabs"             ,"Pabs"             ,"Pabsz"            ,"Pabs"             ),
	ident_0x00000304(0x00000304, 0x00000304, "fZug"             ,"fDrgt"            ,"Tirage"           ,"tiro"             ,"Tir.F"            ,"f trek"           ,"Drag"             ,"j. tah"           ,"F.huz."           ,"fTr�k"            ),
	ident_0x0000030A(0x0000030A, 0x0000030A, "E-Zug"            ,"Ext-draught"      ,"E-Tir"            ,"text"             ,"TirEst "          ,"E-trk"            ,"Edrft"            ,"E-tah"            ,"K- huz."          ,"Ext-tr�k"         ),
	ident_0x0000030B(0x0000030B, 0x0000030B, "E-DeltaP"         ,"Ext Delta-P"      ,"E-DeltaP"         ,"DeltaP"           ,"DeltaPEst"        ,"E-DeltaP"         ,"E-DeltaP"         ,"E-DeltaP"         ,"K-DeltaP"         ,"E-DeltaP"         ),
	ident_0x0000030D(0x0000030D, 0x0000030D, "E-4DeltaP"        ,"Ext-4Pa"          ,"E-4DeltaP"        ,"4DeltaP-E"        ,"4DeltaPEst"       ,"E-4DeltaP"        ,"E-4DeltaP"        ,"E-4DeltaP"        ,"Ext-4Pa"          ,"E-4DeltaP"        ),
	ident_0x0000030E(0x0000030E, 0x0000030E, "Druck"            ,"Press."           ,"Pres."            ,"Pres."            ,"Press"            ,"druk"             ,"Press. "          ,"Tlak"             ,"Nyom�s"           ,"Tryk"             ),
	ident_0x00000401(0x00000401, 0x00000401, "Gesw"             ,"Speed"            ,"Vitesse"          ,"Velocidad"        ,"Velocit�"         ,"Snelh."           ,"Hastig."          ,"rychlost"         ,"L�gseb."          ,"Hastighed"        ),
	ident_0x00000501(0x00000501, 0x00000501, "Pump"             ,"Pump"             ,"Pompe"            ,"bomba"            ,"Pompa"            ,"Pomp"             ,"Pump"             ,"Pumpa"            ,"Pumpa"            ,"Pumpe"            ),
	ident_0x00000601(0x00000601, 0x00000601, "UAkku"            ,"BatV"             ,"Vacc"             ,"Vbat"             ,"Vaccu"            ,"Vacc"             ,"VAcku"            ,"U aku"            ,"Uakku"            ,"BatV"             ),
	ident_0x00000901(0x00000901, 0x00000901, "O2"               ,"O2"               ,"O2"               ,"O2"               ,"O2"               ,"O2"               ,"O2"               ,"O2"               ,"O2"               ,"O2"               ),
	ident_0x00000902(0x00000902, 0x00000902, "CO"               ,"CO"               ,"CO"               ,"CO"               ,"CO"               ,"CO"               ,"CO"               ,"CO"               ,"CO"               ,"CO"               ),
	ident_0x00000903(0x00000903, 0x00000903, "COumg"            ,"AmbCO"            ,"COam"             ,"COamb"            ,"COamb"            ,"COomg"            ,"omgCO"            ,"COok"             ,"COkny"            ,"omgCO"            ),
	ident_0x00000904(0x00000904, 0x00000904, "COunv"            ,"uCO"              ,"COnd"             ,"COcor"            ,"uCO"              ,"COonv"            ,"outCO"            ,"COner"            ,"COh�g"            ,"uCO"              ),
	ident_0x00000905(0x00000905, 0x00000905, "H2"               ,"H2"               ,"H2"               ,"H2"               ,"H2"               ,"H2"               ,"H2"               ,"H2"               ,"H2"               ,"H2"               ),
	ident_0x00000906(0x00000906, 0x00000906, "NO"               ,"NO"               ,"NO"               ,"NO"               ,"NO"               ,"NO"               ,"NO"               ,"NO"               ,"NO"               ,"NO"               ),
	ident_0x00000907(0x00000907, 0x00000907, "NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ,"NO2"              ),
	ident_0x00000908(0x00000908, 0x00000908, "SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ,"SO2"              ),
	ident_0x00000909(0x00000909, 0x00000909, "CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ,"CO2"              ),
	ident_0x0000090A(0x0000090A, 0x0000090A, "CxHy"             ,"HC"               ,"CxHy"             ,"HC"               ,"HC"               ,"CxHy"             ,"HC"               ,"CxHy"             ,"CxHy"             ,"HC"               ),
	ident_0x0000090B(0x0000090B, 0x0000090B, "H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ,"H2S"              ),
	ident_0x0000090C(0x0000090C, 0x0000090C, "O2Bez."           ,"O2ref."           ,"O2 r�f"           ,"O2ref."           ,"O2 Rif."          ,"O2ref."           ,"O2ref."           ,"O2vzt"            ,"O2hiv"            ,"O2 ref."          ),
	ident_0x0000090D(0x0000090D, 0x0000090D, "CO2 max"          ,"CO2 max"          ,"CO2 max."         ,"CO2 m�x."         ,"CO2 t"            ,"CO2 max."         ,"CO2max."          ,"CO2 max"          ,"CO2 max."         ,"CO2max"           ),
	ident_0x0000090F(0x0000090F, 0x0000090F, "CO2um"            ,"AmCO2"            ,"CO2am"            ,"CO2amb"           ,"CO2 a"            ,"CO2om"            ,"omCO2"            ,"CO2ok"            ,"CO2kny"           ,"omgCO2"           ),
	ident_0x00000910(0x00000910, 0x00000910, "O2Zul"            ,"O2air"            ,"O2air"            ,"O2pri"            ,"O2Aria"           ,"O2toev"           ,"O2luft"           ,"O2priv"           ,"O2lev"            ,"O2omg"            ),
	ident_0x00000911(0x00000911, 0x00000911, "CO2IR"            ,"CO2IR"            ,"CO2IR"            ,"CO2IR"            ,"CO2IR"            ,"CO2ir"            ,"CO2IR"            ,"CO2IR"            ,"CO2IR"            ,"CO2IR"            ),
	ident_0x00000912(0x00000912, 0x00000912, "CxHyu"            ,"Ambient CxHy"     ,"HCamb"            ,"HCamb"            ,"HC a"             ,"CxHyomg"          ,"omgHC"            ,"amHC"             ,"CH4/C3H8"         ,"omgHC"            ),
	ident_0x00000913(0x00000913, 0x00000913, "COmax"            ,"COmax"            ,"COmax"            ,"COm�x"            ,"COmax"            ,"COmax"            ,"COmax"            ,"COmax"            ,"COmax"            ,"Comaks."          ),
	ident_0x00000914(0x00000914, 0x00000914, "COumg"            ,"AmbCO"            ,"COam"             ,"COamb"            ,"COamb"            ,"COomg"            ,"omgCO"            ,"COok"             ,"COkny"            ,"omgCO"            ),
	ident_0x0000091B(0x0000091B, 0x0000091B, "O2�"              ,"O2�"              ,"O2�"              ,"O2�"              ,"O2�"              ,"O2�"              ,""                 ,"O2�"              ,"O2�"              ,"O2�"              ),
	ident_0x0000091C(0x0000091C, 0x0000091C, "CO�"              ,"CO�"              ,"CO�"              ,""                 ,""                 ,"CO�"              ,""                 ,"CO�"              ,""                 ,""                 ),
	ident_0x00000C02(0x00000C02, 0x00000C02, "Dauer"            ,"Time"             ,"Dur�e"            ,"Hora"             ,"Ora"              ,"Tijd"             ,"Tid"              ,"Cas:"             ,"�ra"              ,"Tid"              ),
	ident_0x00001F03(0x00001F03, 0x00001F03, "Verd."            ,"Dilution"         ,"Dilut."           ,"Diluci�n"         ,"Diluiz."          ,"Verdun."          ,"Dlution"          ,"Reden�"           ,"hig�t�s"          ,"Fortynd"          ),
	ident_0x00020400(0x00020400, 0x00020400, "Gesw"             ,"Vel"              ,"Vit."             ,"Vel."             ,"Veloc."           ,"Snelh."           ,"Hastig."          ,"m/s"              ,"L�gseb."          ,"Hast"             ),
	ident_0x00020500(0x00020500, 0x00020500, "Vols"             ,"Vol"              ,"D�bit v."         ,"Caudal"           ,"Port"             ,"Vols"             ,"Vol"              ,"Prutok"           ,"Vols"             ,"Vols"             ),
	ident_0x0002090E(0x0002090E, 0x0002090E, "O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ,"O2N"              ),
	ident_0x00020915(0x00020915, 0x00020915, "NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ,"NOx"              ),
	ident_0x00020A02(0x00020A02, 0x00020A02, "MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ,"MCO"              ),
	ident_0x00020A08(0x00020A08, 0x00020A08, "MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ,"MSO2"             ),
	ident_0x00020A0B(0x00020A0B, 0x00020A0B, "MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ,"MH2S"             ),
	ident_0x00020A11(0x00020A11, 0x00020A11, "MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ,"MCO2IR"           ),
	ident_0x00020A15(0x00020A15, 0x00020A15, "MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ,"MNOx"             ),
	ident_0x00020B01(0x00020B01, 0x00020B01, "qA"               ,"qAnet"            ,"P PCS"            ,"qA"               ,"Qs"               ,"qA"               ,"qAnet"            ,"qA"               ,"qA"               ,"qAnet"            ),
	ident_0x00020B02(0x00020B02, 0x00020B02, "qA +"             ,"qAgr."            ,"P PCI"            ,"qAbr."            ,"diff"             ,"qA+"              ,"qAgr."            ,"qA +"             ,"qA+"              ,"qAgr."            ),
	ident_0x00020B03(0x00020B03, 0x00020B03, "eta"              ,"Effn"             ,"R PCS"            ,"REN"              ,"eta "             ,"eta"              ,"Effn"             ,"eta"              ,"eta"              ,"Effn"             ),
	ident_0x00020B04(0x00020B04, 0x00020B04, "eta +"            ,"Effg"             ,"R PCI"            ,"RENbr."           ,"Rend"             ,"eta +"            ,"Effg"             ,"eta +"            ,"eta+"             ,"Effg"             ),
	ident_0x00020B05(0x00020B05, 0x00020B05, "qA25"             ,"qA25"             ,"qA25"             ,"qA25"             ,"qA25"             ,"qA25"             ,"qA25"             ,"qA25"             ,"qA25"             ,"QA25"             ),
	ident_0x00020B06(0x00020B06, 0x00020B06, "qR"               ,"qR"               ,"qR"               ,"qR"               ,"qR"               ,"qR"               ,"qR"               ,"qR"               ,"qR"               ,"QR"               ),
	ident_0x00020B08(0x00020B08, 0x00020B08, "qA�"              ,"qA�"              ,"qA�"              ,"qA�"              ,"qA�"              ,"qA�"              ,""                 ,"qA�"              ,"qA�"              ,"qA�"              ),
	ident_0x00020B0F(0x00020B0F, 0x00020B0F, "ET"               ,"ET"               ,"ET"               ,""                 ,"ET"               ,"ET"               ,""                 ,"ET"               ,"ET"               ,"ET"               ),
	ident_0x00021280(0x00021280, 0x00021280, "GI"               ,"GI"               ,"GI"               ,"PI"               ,"GI"               ,"GI"               ,"PI"               ,"�nik"             ,"GI"               ,"GI"               ),
	ident_0x00021281(0x00021281, 0x00021281, "ExAir"            ,"ExAir"            ,"ExAir"            ,"Eaire"            ,"ExAir"            ,"ExLucht"          ,"ExAir"            ,"ExVz "            ,"ExAir"            ,"Exluft"           ),
	ident_0x00021282(0x00021282, 0x00021282, "lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ),
	ident_0x00021283(0x00021283, 0x00021283, "ratio"            ,"Ratio"            ,"ratio"            ,"Ratio"            ,"ratio"            ,"Ratio"            ,"kvot"             ,"pomer"            ,"ar�ny"            ,"Forhold"          ),
	ident_0x00021A02(0x00021A02, 0x00021A02, "COred"            ,"COred"            ,"COred"            ,"COred"            ,"COred"            ,"COred"            ,"COred"            ,"COred"            ,"COred"            ,"COred"            ),
	ident_0x00021A15(0x00021A15, 0x00021A15, "NOxunv"           ,"uNOx"             ,"NOXnd"            ,""                 ,"uNOx"             ,"NOxonv"           ,""                 ,"uNOx"             ,""                 ,""                 ),
	ident_0x00021F04(0x00021F04, 0x00021F04, "qA Punkte"        ,"qA Points"        ,"Points qA "       ,"Puntos qA"        ,"Punti qA"         ,"qA punten"        ,""                 ,"qA Body"          ,"qA Points"        ,"qA Punkter"       ),
	ident_0x01000100(0x01000100, 0x01000100, "T2"               ,"T2"               ,"T2"               ,"T2"               ,"T2"               ,"T2"               ,"T2"               ,"T2"               ,"T2"               ,"T2"               ),
	ident_0x01000102(0x01000102, 0x01000102, "WTT"              ,"HCT"              ,"TEC"              ,"TCR"              ,"Tman"             ,"Tketel"           ,"PVT"              ,"Ttm"              ,"Tv�z"             ,"HCT"              ),
	ident_0x01000104(0x01000104, 0x01000104, "TSens"            ,"TSens"            ,""                 ,"TSens"            ,""                 ,""                 ,""                 ,"TSens"            ,""                 ,""                 ),
	ident_0x0100030D(0x0100030D, 0x0100030D, "max"              ,"max"              ,"max "             ,"m�x"              ,"max"              ,"max"              ,""                 ,"max"              ,"max"              ,"max"              ),
	ident_0x01020505(0x01020505, 0x01020505, "Leckr"            ,"leakage"          ,"Fuite"            ,"fuga"             ,"Perdita"          ,"Lekver."          ,""                 ,"�nik"             ,"Sziv�rg"          ,"l�kage"           ),
	ident_0x01021282(0x01021282, 0x01021282, "lambdaMin."       ,"lambdaMin."       ,""                 ,""                 ,""                 ,""                 ,""                 ,"lambdaMin."       ,"lambdaMin."       ,""                 ),
	ident_0x02000302(0x02000302, 0x02000302, "Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ),
	ident_0x0200030D(0x0200030D, 0x0200030D, "min"              ,"min"              ,"min"              ,"m�n"              ,"min"              ,"min"              ,""                 ,"min"              ,"min"              ,"min."             ),
	ident_0x02020100(0x02020100, 0x02020100, "Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ,"Deltat"           ),
	ident_0x03000302(0x03000302, 0x03000302, "P1"               ,"P1"               ,"P1"               ,"P1"               ,"P1"               ,"P1"               ,""                 ,"P1"               ,"P1"               ,"P1"               ),
	ident_0x03020100(0x03020100, 0x03020100, "Netto"            ,"Nett"             ,"Nette"            ,"Neto"             ,"Netto"            ,"Netto"            ,"Netto"            ,"Netto"            ,"Netto"            ,"Nett"             ),
	ident_0x04000302(0x04000302, 0x04000302, "P1"               ,"P1"               ,"P1"               ,"P1"               ,"P1"               ,"P1"               ,""                 ,"P1"               ,"P1"               ,"P1"               ),
	ident_0x05000302(0x05000302, 0x05000302, "P2"               ,"P2"               ,"P2"               ,"P2"               ,"P2"               ,"P2"               ,""                 ,"P2"               ,"P2"               ,"P2"               ),
	ident_0x06020302(0x06020302, 0x06020302, "DeltaP"           ,"DeltaP"           ,"DeltaP"           ,"DeltaP"           ,"DeltaP"           ,"dP"               ,""                 ,"DeltaP"           ,"DeltaP"           ,"DeltaP"           ),
	ident_0x07020302(0x07020302, 0x07020302, "DeltaPmax"        ,"DeltaPmax"        ,"DeltaPmax"        ,"DeltaPm�x"        ,"DeltaPmax"        ,"dPmax"            ,""                 ,"DeltaPmax"        ,"DeltaPmax"        ,"DeltaPmax"        ),
	ident_0x80000C01(0x80000C01, 0x80000C01, "Bstd."            ,"Ophr"             ,"H util"           ,"H.fnc."           ,"H lav."           ,"B.uren"           ,"Ophr"             ,"ProvH."           ,"T ido"            ,"Drift"            ),
	ident_0x80010001(0x80010001, 0x80010001, "Staurohr"         ,"Pitot factor"     ,"Tube Pitot"       ,"Factor pitot"     ,"Fattore pitot"    ,"Pitot buis"       ,"Pitot"            ,"Pitotova trubice" ,"Pitot"            ,"Pitot faktor"     ),
	ident_0x80010002(0x80010002, 0x80010002, "Korrektur"        ,"Offset factor"    ,"Correction"       ,"Factor offset"    ,"Fattore offset"   ,"Correctie"        ,"Offset"           ,"Korekce"          ,"Offset"           ,"Offset faktor"    ),
	ident_0x80010108(0x80010108, 0x80010108, "WTT"              ,"HCT"              ,"TEC"              ,"TCR"              ,"Tman"             ,"Tketel"           ,"PVT"              ,"Ttm"              ,"Tv�z"             ,"HCT"              ),
	ident_0x80010118(0x80010118, 0x80010118, "T.VL"             ,"Tmp/a"            ,"�C air comb"      ,"Temp/amb"         ,"Temp./aria ambiente","Tv"               ,"Tmp/omg"          ,"teplota TV"       ,"Tk�rny"           ,"Tmp/o"          ),
	ident_0x8001011A(0x8001011A, 0x8001011A, "E.F"              ,"E.F"              ,"E.F"              ,"E.F."             ,""                 ,"E.F"              ,"K.V."             ,"E.F"              ,"E.F"              ,"E.F"              ),
	ident_0x8001011B(0x8001011B, 0x8001011B, "E.C"              ,"E.C"              ,"E.C"              ,"E.C."             ,""                 ,"E.C"              ,"V.V."             ,"E.C"              ,"E.C"              ,"E.C"              ),
	ident_0x8001011D(0x8001011D, 0x8001011D, "Umgeb.Temp."      ,"Ambient Temp"     ,"Temp amb"         ,"Temp. amb."       ,"Temp. Amb."       ,"Omg.temp."        ,""                 ,"Okoln� tep"       ,"K�rny. hom."      ,"Omgiv. temp"      ),
	ident_0x8001011E(0x8001011E, 0x8001011E, "Links Temp."      ,"Left Temp."       ,"Temp gauche"      ,"T� izq."          ,"Temp. Sx"         ,"Links temp."      ,""                 ,"Lev� tep."        ,"Balold.hom."      ,"Venstre temp"     ),
	ident_0x8001011F(0x8001011F, 0x8001011F, "Aussen-Temp."     ,"Outdoor Temp"     ,"Temp ext"         ,"Temperatura externa","Temp. esterna"    ,"Buitentemp."      ,""                 ,"Venkovn� tep"     ,"K�lso hom�rs�klet","Udend�rs temp." ),
	ident_0x80010120(0x80010120, 0x80010120, "Referenz AT"      ,"reference FT"     ,"TF r�f�rence"     ,"referencia TH"    ,"TF riferimento"   ,"Referentie RT"    ,""                 ,"referencn� TS"    ,"ref.FT"           ,"refence Omg. T"   ),
	ident_0x80010121(0x80010121, 0x80010121, "Umgeb.Temp."      ,"Ambient Temp"     ,"Temp�rature ambiante","Temp.ambiente"    ,"Temp. ambiente"   ,"Omg. temp."       ,""                 ,"Okoln� tep"       ,"K�rnyezeti hom�rs�klet","Omgivende temp."  ),
	ident_0x80010123(0x80010123, 0x80010123, "Aussen-Temp."     ,"Outdoor Temp"     ,"Temp ext"         ,"Temperatura externa","Temp. esterna"    ,"Buitentemp."      ,""                 ,"Venkovn� tep"     ,"K�lso hom�rs�klet","Udend�rs temp." ),
	ident_0x80010201(0x80010201, 0x80010201, "F.VL"             ,"Amb H"            ,"%HR AC"           ,"H./amb"           ,"URaria"           ,"RV ol."           ,"Rf/omg"           ,"Vlhk."            ,"rH"               ,"Fugt o"           ),
	ident_0x80010300(0x80010300, 0x80010300, "Bet.dr"           ,"Opr.pr"           ,"P util�"          ,"Pres.func."       ,"Op.press"         ,"Werkdruk"         ,""                 ,"Opr.tl"           ,"Nyomt.c�l"        ,"Drft.Tr"          ),
	ident_0x80010303(0x80010303, 0x80010303, "Pabs"             ,"Pabs"             ,"P abs"            ,"Pabs"             ,"P ass"            ,"Pabs"             ,"Pabs"             ,"Pabs"             ,"Pabsz"            ,"Pabs"             ),
	ident_0x80010305(0x80010305, 0x80010305, "�lDr"             ,"Oil pressure:"    ,"P fioul"          ,"Pot. C."          ,"Pre.O"            ,"Oliedr."          ,"Olj p"            ,"p ol"             ,"Olajny."          ,"Olietryk"         ),
	ident_0x80010306(0x80010306, 0x80010306, "GasDr"            ,"GasPr"            ,"P gaz"            ,"Pot.G."           ,"Pre.G"            ,"Gasdr."           ,"Gas p"            ,"p gas"            ,"G�zny."           ,"Gastr"            ),
	ident_0x80010307(0x80010307, 0x80010307, "Barom"            ,"Barom"            ,"Barom"            ,"Pbaro."           ,"Barom"            ,"Barom"            ,"Barom"            ,"barom"            ,"Bar.ny"           ,"Btryk"            ),
	ident_0x80010308(0x80010308, 0x80010308, "Deltap2"          ,"Deltap2"          ,"Pstat"            ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ,"Deltap2"          ),
	ident_0x80010309(0x80010309, 0x80010309, "Deltap"           ,"D P"              ,"Pdyn"             ,"Deltap"           ,"Deltap"           ,"Deltap"           ,"Deltap"           ,"Deltap"           ,"DeltaP"           ,"Deltap"           ),
	ident_0x8001030C(0x8001030C, 0x8001030C, "Diff-Druck"       ,"Diff-Press."      ,"P diff"           ,"P.Dif."           ,"DeltaP"           ,"Verschildruk"     ,"Diff.tryck"       ,"dif.tlak"         ,"Diff. nyom�s"     ,"Diff.tryk"        ),
	ident_0x80010503(0x80010503, 0x80010503, "�lDu"             ,"OilFl"            ,"Dfioul"           ,"Q. co."           ,"Por.O"            ,"Oldeb."           ,"OljFl"            ,"v.ol"             ,"Olajf."           ,"Flow"             ),
	ident_0x80010506(0x80010506, 0x80010506, "W.durchsatz"      ,"Wflow"            ,"Deau"             ,"Cagua"            ,""                 ,"W.str"            ,""                 ,"Vpru"             ,"V�tf."            ,"Vflow"            ),
	ident_0x8001090C(0x8001090C, 0x8001090C, "O2ref"            ,"O2ref"            ,"O2 r�f"           ,"O2ref"            ,"O2rif."           ,"O2ref"            ,"O2ref"            ,"O2ref"            ,"O2ref"            ,"O2ref"            ),
	ident_0x80010918(0x80010918, 0x80010918, "NO2 zus"          ,"NO2 Ad"           ,"%NO2 "            ,"NO2add"           ,"Co.NO2"           ,"NO2 +"            ,"NO2 add"          ,"NO2 kor"          ,"NO2+"             ,"NO2 +"            ),
	ident_0x80010C02(0x80010C02, 0x80010C02, "Stabilisierungszeit","Stability time"   ,"Temps stabil�"    ,"Tiempo estab."    ,"Tempo stabilizz"  ,"Stabilisatietijd" ,""                 ,"Doba stabil"      ,"Stabiliz�ci�ds ido","Stabilitetstid"),
	ident_0x80010D01(0x80010D01, 0x80010D01, "H� NN"            ,"Alt"              ,"Alti"             ,"N.mar"            ,"Alt"              ,"Hoogte"           ,"M.�.h"            ,"m n.m."           ,"Tszfm"            ,"H�jde"            ),
	ident_0x80010D02(0x80010D02, 0x80010D02, "Durchm."          ,"diameter"         ,"Diam�tre"         ,"Di�metro"         ,"Diametro"         ,"Diameter"         ,"diam."            ,"prumer"           ,"�tm�ro"           ,"diameter"         ),
	ident_0x80010D03(0x80010D03, 0x80010D03, "Quadrat A"        ,"square dim."      ,"Carr� A"          ,"Medidas al cuadrado","Lato"             ,"Kwadraat A"       ,"Kvadrat"          ,"druh� mocnina A"  ,"N�gyzet"          ,"firkantet dim." ),
	ident_0x80010D04(0x80010D04, 0x80010D04, "Seite_A"          ,"Rect_A"           ,"C�t� A"           ,"Rect_A"           ,"Base"             ,"ZijdeA"           ,"Rekt_A"           ,"Str A"            ,"A "               ,"SideA"            ),
	ident_0x80010D05(0x80010D05, 0x80010D05, "Seite_B"          ,"Rect_B"           ,"C�t� B"           ,"Rect_B"           ,"Altez."           ,"ZijdeB"           ,"Rekt_B"           ,"Str B"            ,"B "               ,"SideB"            ),
	ident_0x80010D06(0x80010D06, 0x80010D06, "L�nge"            ,"Length"           ,"Longueur"         ,"Longitud"         ,"Lunghezza"        ,"Lengte"           ,"Length"           ,"D�lka"            ,"Hossz"            ,"L�ngde "          ),
	ident_0x80010D07(0x80010D07, 0x80010D07, "�"                ,"�"                ,"�"                ,"�"                ,"�"                ,"�"                ,""                 ,"�"                ,"�"                ,"�"                ),
	ident_0x80010D08(0x80010D08, 0x80010D08, "H�he h"           ,"Height h"         ,"Hauteur h"        ,"Altura h"         ,"Altezza h"        ,"Hoogte h"         ,""                 ,"V��ka v"          ,"Cs�cs (h)"        ,"H�jde h"          ),
	ident_0x80010D09(0x80010D09, 0x80010D09, "Breite b"         ,"Width w"          ,"Largeur "        ,"Ancho w"          ,"Larghezza w"      ,"Breedte b"        ,""                 ,"��rka s"          ,"sz�less�g (w)"    ,"Bredde b"          ),
	ident_0x80010D0A(0x80010D0A, 0x80010D0A, "Tiefe t"          ,"Depth d"          ,"Profondeur p"     ,"Largo d"          ,"Profondit� d"     ,"Diepte d"         ,""                 ,"Hloubka h"        ,"M�lys�g (d)"      ,"Dybde d"          ),
	ident_0x80010D0B(0x80010D0B, 0x80010D0B, "H�he h"           ,"Height h"         ,"Hauteur h"        ,"Altura h"         ,"Altezza h"        ,"Hoogte h"         ,""                 ,"V��ka v"          ,"Cs�cs (h)"        ,"H�jde h"          ),
	ident_0x80010E00(0x80010E00, 0x80010E00, "Fl�che"           ,"Area"             ,"Surf"             ,"Area"             ,"Area"             ,"Opper."           ,"Area"             ,"povrch"           ,"Ter."             ,"Areal"            ),
	ident_0x80010F01(0x80010F01, 0x80010F01, "Gasm."            ,"Gasa."            ,"Q gaz"            ,"Cantidad de Gas"  ,"Q.t� gas"         ,"Gashoev."         ,""                 ,"Mno�stv� plynu"   ,"Gasa."            ,"Gasm�ngde"        ),
	ident_0x80011102(0x80011102, 0x80011102, "Luftdichte"       ,"Air Density"      ,"Densit� air"      ,"Densidad aire"    ,"Densit� aria"     ,"Luchtdichtheid"   ,""                 ,"Hustota vzduchu"  ,"L�gsurus�g"       ,"Luftt�thed"       ),
	ident_0x80011903(0x80011903, 0x80011903, "K. Leistung"      ,"Boiler power"     ,"Puissance"        ,"P.com"            ,"Potenza bruciatore","Branderverm."     ,"Panneffekt"       ,"K. vykon"         ,"Teljes�tm�ny"     ,"Br�ndr eff"      ),
	ident_0x80011B01(0x80011B01, 0x80011B01, "Ru�zahl 1"        ,"Smoke 1"          ,"Suie 1"           ,"Opacidad 1"       ,"Indice fumosit� 1","Roetgetal 1"      ,""                 ,"Sazov� c1"        ,"Korom1"           ,"Sodtal 1"         ),
	ident_0x80020119(0x80020119, 0x80020119, "Tp.VL"            ,"Dew/a"            ,"Td AC"            ,"P�r/am"           ,"TDaria"           ,"Dp. ol"           ,"td/omg"           ,"RB TV"            ,"Hp."              ,"Dug/o"            ),
	ident_0x80020502(0x80020502, 0x80020502, "GasDu"            ,"Gasr."            ,"D Gaz"            ,"Qgas"             ,"Por.G"            ,"Gasdeb."          ,"Gasfl"            ,"Prutok"           ,"G�z�tf"           ,"Gasflow"          ),
	ident_0x80020504(0x80020504, 0x80020504, "Fluss"            ,"flow"             ,"D�bit"            ,"velocidad"        ,"Portata"          ,"Stroom"           ,"fl�d."            ,"Proud"            ,"�raml�s"          ,"Flow"             ),
	ident_0x80020E1E(0x80020E1E, 0x80020E1E, "Links"            ,"Left"             ,"Gauche"           ,"Izquierda"        ,"Sinistra"         ,"Links"            ,""                 ,"Levy"             ,"Bal"              ,"Venstre"          ),
	ident_0x80020F02(0x80020F02, 0x80020F02, "Volumen"          ,"Volume"           ,"Volumes"          ,"Volumen"          ,"Volume"           ,"Volume"           ,""                 ,"Objem "           ,"T�meg"            ,"Volume"           ),
	ident_0x80021901(0x80021901, 0x80021901, "P �l"             ,"Oil P"            ,"kW FOD"           ,"P.com"            ,"Pot.O"            ,"Verm."            ,"OljP"             ,"P ol"             ,"Telj"             ,"OilP"             ),
	ident_0x80021902(0x80021902, 0x80021902, "PGas"             ,"GasPgr"           ,"kW gaz"           ,"P. Gas"           ,"Pot.G"            ,"Pgas"             ,"GasP"             ,"Pply"             ,"Telj."            ,"Gas P"            ),
	ident_0x80021903(0x80021903, 0x80021903, "K.Lst"            ,"Power"            ,"Puiss"            ,"Ptncia"           ,"Pot."             ,"Verm."            ,"Eff."             ,"K.v�k"            ,"Telj"             ,"Power"            ),
	ident_0x80021904(0x80021904, 0x80021904, "SLstg"            ,"SPwr"             ,"Psani"            ,"ACS"              ,""                 ,"S.Bel"            ,""                 ,"SPwr"             ,"E�.H�."           ,"Seff"             ),
	ident_0x80021F04(0x80021F04, 0x80021F04, "qA P."            ,"qA P."            ,"qA P."            ,"qA P."            ,"qA P."            ,"qA P"             ,""                 ,"qA B."            ,"qA P."            ,"qA P."            ),
	ident_0x80021F05(0x80021F05, 0x80021F05, "OberP"            ,"Sur.P"            ,"Sur.P"            ,"P.sup"            ,"Sup.P"            ,"Opp.P"            ,""                 ,"Pov B"            ,"Sur.P"            ,"Ov.P"             ),
	ident_0x80021F06(0x80021F06, 0x80021F06, "VentP"            ,"VentP"            ,"VentP"            ,"P.ven"            ,"VentP"            ,"VentP"            ,""                 ,"VentB"            ,"VentP"            ,"VentP"            ),
	ident_0x80021F07(0x80021F07, 0x80021F07, "qLs"              ,"qLs"              ,"qLs"              ,"qLs"              ,"qLs"              ,"qLs"              ,""                 ,"qLs"              ,"qLs"              ,"qLs"              ),
	ident_0x80021F08(0x80021F08, 0x80021F08, "qst"              ,"qst"              ,"qst"              ,"qst"              ,"qst"              ,"qst"              ,""                 ,"qst"              ,"qst"              ,"qst"              ),
	ident_0x80050000(0x80050000, 0x80050000, "Erg. Gasarmaturdichtigkeitstest","Result Let By Test","R�sultat pression r�elle","Resultado Estanq. Instalaci�n","Esito prova preliminare","Res. primaire controle",""            ,"Test tesnosti plynov�ho veden�","T�m�tetts�g vizsg�lat eredm�nye","Resultat t�thedstest gasarmatur"),
	ident_0x80051B02(0x80051B02, 0x80051B02, "�lderivate"       ,"Oil deposits"     ,"Fioul imbr�l�:"   ,"Oleod"            ,"Fumosit� OK?"     ,"Oliederiv."       ,"Oljederivat "     ,"Ol. deriv�ty"     ,"Olajsz�rm."       ,"Oilederivat"      ),
	ident_0x80051F09(0x80051F09, 0x80051F09, "K - Faktor"       ,"K - Factor"       ,"Facteur K"        ,"Factor K"         ,"Fattore K"        ,"K - Factor"       ,""                 ,"K - Faktor"       ,"K Faktor"         ,"K-faktor"         ),
	ident_0x80052001(0x80052001, 0x80052001, "CO<= 1000ppm?"    ,"CO =1000ppm?"     ,"CO<=1000ppm?"     ,"CO=1000ppm ?"     ,"CO =1000ppm?"     ,"CO<=1000ppm?"     ,"CO =1000ppm?"     ,"CO=1000ppm ?"     ,"CO =1000ppm?"     ,"CO =1000ppm?"     ),
	ident_0x8101011E(0x8101011E, 0x8101011E, "Vo.ob. Temp."     ,"Fr.top Temp."     ,"Temp av haut"     ,"T� fron.sup."     ,"Temp. A.Sup."     ,"Vr.bv temp."      ,""                 ,"Vep.nah.tep."     ,"Els.fent.hom"     ,"Fr.top temp."     ),
	ident_0x81010300(0x81010300, 0x81010300, "Pr�fdr"           ,"TestPr."          ,"Pess"             ,"Testp"            ,"P.Prova"          ,"Prf.drk"          ,""                 ,"Test"             ,"P.Test "          ,"TestPr."          ),
	ident_0x81010C02(0x81010C02, 0x81010C02, "Stabilisierungszeit","Stability time"   ,"Temps stabil�"    ,"Tiempo estab."    ,"Tempo stabilizz"  ,"Stabilisatietijd" ,""                 ,"Doba stabil"      ,"Stabiliz�ci�ds ido","Stabilitetstid"),
	ident_0x81010D06(0x81010D06, 0x81010D06, "L�nge"            ,"Length"           ,"Longueur"         ,"Longitud"         ,"Lunghezza"        ,"Lengte"           ,"Length"           ,"D�lka"            ,"Hossz"            ,"L�ngde "          ),
	ident_0x81010D07(0x81010D07, 0x81010D07, "� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ,"� 2"              ),
	ident_0x81011B01(0x81011B01, 0x81011B01, "Ru�zahl 2"        ,"Smoke 2"          ,"Suie 2"           ,"Opacidad 2"       ,"Indice fumosit� 2","Roetgetal 2"      ,""                 ,"Sazov� c2"        ,"Korom 2"          ,"Sodtal 2"         ),
	ident_0x81020C01(0x81020C01, 0x81020C01, "Bstd.lS"          ,"Ophrsls"          ,"H utilis."        ,"H.func."          ,"Ore lavoro"       ,"Bedr.uren"        ,"Ophr"             ,"Provozn� hodiny"  ,"�zem�ra"          ,"Driftstid"        ),
	ident_0x81020E1E(0x81020E1E, 0x81020E1E, "Vorne oben"       ,"Front top"        ,"Avant haut"       ,"Frontal sup."     ,"Anter. sup."      ,"Voor boven"       ,""                 ,"Vepredu nah"      ,"El�l fent"        ,"Front top"        ),
	ident_0x81021902(0x81021902, 0x81021902, "Capacity(net)"    ,"Capacity(net)"    ,"Capacit�(net)"    ,"Capacidad (neto)" ,"Capacit�"         ,"Capaciteit"       ,""                 ,"Capacity(net)"    ,"Kapacit�s "       ,"Kapacitet(net)"   ),
	ident_0x81050000(0x81050000, 0x81050000, "Erg. Gebrauchsf�higkeit","Result of test"   ,"R�sultat d�bit fuite","Resultado idoneidad","Esito prova"      ,"Res. controle goede werking",""                 ,"V�sledek testu"   ,"Vizsg�lati eredm�ny","Resultat af test"),
	ident_0x8105091A(0x8105091A, 0x8105091A, "Pr�fgas"          ,"Testgas"          ,"Gaz �talon"       ,"Gas patr�n"       ,"Test Gas"         ,"Testgas"          ,""                 ,"Zku�eb. plyn"     ,"Tesztg�z"         ,"Testgas"          ),
	ident_0x81052001(0x81052001, 0x81052001, "Ges. Vorgaben?"   ,"etac =etaDPR412?" ,"Norme ?"          ,"etac=etaDPR412 ?" ,"etac =etaDPR412?" ,"etac =etaDPR412?" ,""                 ,"etac=etaDPR412 ?" ,"etac =etaDPR412?" ,"etac =etaDPR412?" ),
	ident_0x8201011E(0x8201011E, 0x8201011E, "Vo.un. Temp."     ,"Fr.down Temp"     ,"Temp av bas"      ,"T� fron.inf."     ,"Temp. A.Inf."     ,"Vr.ond. temp"     ,""                 ,"Vep.dole tep"     ,"Els.lent.hom"     ,"Fr.ned temp."     ),
	ident_0x82010300(0x82010300, 0x82010300, "Pr�fdr"           ,"TestPr."          ,"Pess"             ,"Testp"            ,"P.Prova"          ,"Prf.drk"          ,""                 ,"Test"             ,"P.Test "          ,"TestPr."          ),
	ident_0x82010C02(0x82010C02, 0x82010C02, "Stabilisierungszeit","Stability time"   ,"Temps stabil�"    ,"Tiempo estab."    ,"Tempo stabilizz"  ,"Stabilisatietijd" ,""                 ,"Doba stabil"      ,"Stabiliz�ci�ds ido","Stabilitetstid"),
	ident_0x82010D06(0x82010D06, 0x82010D06, "L�nge"            ,"Length"           ,"Longueur"         ,"Longitud"         ,"Lunghezza"        ,"Lengte"           ,"Length"           ,"D�lka"            ,"Hossz"            ,"L�ngde "          ),
	ident_0x82010D07(0x82010D07, 0x82010D07, "� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ,"� 3"              ),
	ident_0x82011B01(0x82011B01, 0x82011B01, "Ru�zahl 3"        ,"Smoke 3"          ,"Suie 3"           ,"Opacidad 3"       ,"Indice fumosit� 3","Roetgetal 3"      ,""                 ,"Sazov� c3"        ,"Korom3"           ,"Sodtal 3"         ),
	ident_0x82020E1E(0x82020E1E, 0x82020E1E, "Vorne unten"      ,"Front down"       ,"Avant bas"        ,"Frontal inf."     ,"Anter. inf."      ,"Voor onder"       ,""                 ,"Vepredu dole"     ,"Elol lent"        ,"Front ned"        ),
	ident_0x82050000(0x82050000, 0x82050000, "Bewertung"        ,"Air Nr. Check"    ,"Evaluation facteur d�air","Comp. N� aire"    ,"Verifica numero aria","Beoordeling"      ,""                 ,"Kontrola"         ,"L�gsz�ks�glet ellenorz�s","Luft nummer tjek" ),
	ident_0x8205091A(0x8205091A, 0x8205091A, "Pr�fgas"          ,"Testgas"          ,"Gaz �talon"       ,"Gas patr�n"       ,"Test Gas"         ,"Testgas"          ,""                 ,"Zku�eb. plyn"     ,"Tesztg�z"         ,"Testgas"          ),
	ident_0x82052001(0x82052001, 0x82052001, "Zust. Isolation?" ,"Isolation"        ,"Isol compl?"      ,"Aislamiento"      ,"Stato coib."      ,"Stat.iso?"        ,"Isalation?"       ,"Izolace"          ,"Lev�laszt�s"      ,"Isolation"        ),
	ident_0x8301011E(0x8301011E, 0x8301011E, "Rechts Temp."     ,"Right Temp."      ,"Temp droite"      ,"T� derecha"       ,"Temp. Dx"         ,"Temp. rechts"     ,""                 ,"Prav� tep."       ,"Jobbold.hom."     ,"H�jre temp."      ),
	ident_0x83010C02(0x83010C02, 0x83010C02, "Stabi (ist)"      ,"ActTime"          ,"Tstab"            ,"Stab"             ,"Efffett"          ,"St.tijd"          ,""                 ,"Ust�lit"          ,"Akt.Ido"          ,"Stabil"           ),
	ident_0x83010D07(0x83010D07, 0x83010D07, "Abgasrohr �"      ,"Pipe �"           ,"Conduit fum�e �"  ,"� Tuber�a"        ,"� Tubo"           ,"Rookgasafvr �"    ,""                 ,"Trubka �"         ,"Cso �"            ,"R�rdiameter"      ),
	ident_0x83020E1E(0x83020E1E, 0x83020E1E, "Rechts"           ,"Right"            ,"Droite"           ,"Derecha"          ,"Destra"           ,"Rechst"           ,""                 ,"Pravy"            ,"Jobb"             ,"H�jre"            ),
	ident_0x83021B01(0x83021B01, 0x83021B01, "Ru�zahl �"        ,"Smoke �"          ,"Suie �"           ,"Opacidad �"       ,"Fumosit�"         ,"Roetgetal �"      ,""                 ,"Sazov� c�"        ,"Korom �"          ,"Sodtal �"         ),
	ident_0x83052001(0x83052001, 0x83052001, "Zust. Regler?"    ,"Reg. devices"     ,"R�gul compl?"     ,"Reg. equipos"     ,"Dispos. regol."   ,"Status reg.?"     ,"Regl. enhet?"     ,"Reg. zar�zen� "   ,"Szab. eszk."      ,"Reg. Enheder"     ),
	ident_0x8401011E(0x8401011E, 0x8401011E, "R�.ob. Temp."     ,"Ba.top Temp."     ,"Temp ar haut"     ,"T� dor.sup."      ,"Temp. P.Sup."     ,"Acht.bv temp"     ,""                 ,"Vz.nah.tep."      ,"H�t.fent.hom"     ,"Ba.top temp."     ),
	ident_0x84010C02(0x84010C02, 0x84010C02, "Messdauer"        ,"Test time"        ,"Dur�e mes."       ,"Duraci�n med."    ,"Durata misura"    ,"Meetduur"         ,""                 ,"Doba meren�"      ,"Teszt ido"        ,"M�levarighed"     ),
	ident_0x84020E1E(0x84020E1E, 0x84020E1E, "R�cks. oben"      ,"Back top"         ,"Arri�re haut"     ,"Dorsal sup."      ,"Post. sup."       ,"Achterz.bvn"      ,""                 ,"Vzadu nahore"     ,"H�ts� fent"       ,"Bag top"          ),
	ident_0x84052001(0x84052001, 0x84052001, "LuftZirkulation?" ,"Room ventil."     ,"Circ d'air?"      ,"Ventil.sala"      ,"Vent. locale"     ,"Lucht circ.?"     ,"Rumsvent.?"       ,"Cirk vzduchu"     ,"K�rny.Lev."       ,"Rum vent."        ),
	ident_0x8501011E(0x8501011E, 0x8501011E, "R�.un. Temp."     ,"Ba.down Temp"     ,"Temp ar bas"      ,"T� dor.inf."      ,"Temp. P.Inf."     ,"Acht.on temp"     ,""                 ,"Vz.dole tep."     ,"H�t.lent.h�m"     ,"Ba.ned. Temp"     ),
	ident_0x85010C02(0x85010C02, 0x85010C02, "Messdauer"        ,"Test time"        ,"Dur�e mes."       ,"Duraci�n med."    ,"Durata misura"    ,"Meetduur"         ,""                 ,"Doba meren�"      ,"Teszt ido"        ,"M�levarighed"     ),
	ident_0x85020E1E(0x85020E1E, 0x85020E1E, "R�cks. unten"     ,"Back down"        ,"Arri�re bas"      ,"Dorsal inf."      ,"Post. inf."       ,"Achterz.ond"      ,""                 ,"Vzadu dole"       ,"H�ts� lent"       ,"Bag ned"          ),
	ident_0x8601011E(0x8601011E, 0x8601011E, "Deckel Temp."     ,"Top Temp."        ,"Temp sup"         ,"T� sup."          ,"Temp. Sup."       ,"Deksel temp."     ,""                 ,"Vrch tep."        ,"Felso hom."       ,"Top temp."        ),
	ident_0x86010C02(0x86010C02, 0x86010C02, "Messdauer"        ,"Test time"        ,"Dur�e mes."       ,"Duraci�n med."    ,"Durata misura"    ,"Meetduur"         ,""                 ,"Doba meren�"      ,"Teszt ido"        ,"M�levarighed"     ),
	ident_0x86020E1E(0x86020E1E, 0x86020E1E, "Deckel"           ,"Top"              ,"Face sup."        ,"Arriba"           ,"Superiore"        ,"Boven"            ,""                 ,"Vrsek"            ,"Fent"             ,"Top"              ),
	ident_0x8701011E(0x8701011E, 0x8701011E, "Boden Temp."      ,"Bottom Temp."     ,"Temp sol"         ,"T� inf."          ,"Temp. Inf."       ,"Bodem temp."      ,""                 ,"Spod tep"         ,"Als� hom."        ,"Bund temp."       ),
	ident_0x87010C02(0x87010C02, 0x87010C02, "Messd (ist)"      ,"Time:"            ,"D mes"            ,"tmed"             ,"Effett"           ,"Meetd"            ,""                 ,"Casm"             ,"Ido:"             ,"Mtid"             ),
	ident_0x87020E1E(0x87020E1E, 0x87020E1E, "Boden"            ,"Bottom"           ,"Sol"              ,"Abajo"            ,"Inferiore"        ,"Onderknt,"        ,""                 ,"Spodek"           ,"Als�"             ,"Bund"             ),
	ident_0x00000000(0x00040C00, 0x00000000,  "",          "",    "",    "",    "",    "",    "",    "",    "",    ""                                                                                                                               );
                
	
	/**
	 * ident number
	 */
	private final int number;

	/**
	 * ident old number
	 */
	private final int number_old;

	/**
	 * ident text in DE
	 */
	private final String text_de;

	/**
	 * ident text in EN
	 */
	private final String text_en;

	/**
	 * ident text in FR
	 */
	private final String text_fr;

	/**
	 * ident text in ES
	 */
	private final String text_es;

	/**
	 * ident text in IT
	 */
	private final String text_it;

	/**
	 * ident text in NL
	 */
	private final String text_nl;

	/**
	 * ident text in SV
	 */
	private final String text_sv;

	/**
	 * ident text in CS
	 */
	private final String text_cs;

	/**
	 * ident text in HU
	 */
	private final String text_hu;

	/**
	 * ident text in DA
	 */
	private final String text_da;

	
	
	/**
	 * Constructor
	 * @param number ident number
	 * @param number_old ident old number
	 * @param text_de ident text in DE
	 * @param text_en ident text in EN
	 * @param text_fr ident text in FR
	 * @param text_es ident text in ES
	 * @param text_it ident text in IT
	 * @param text_nl ident text in NL
	 * @param text_sv ident text in SV
	 * @param text_cs ident text in CS
	 * @param text_hu ident text in HU
	 * @param text_da ident text in DA
	 */
	private Idents(int number, int number_old, String text_de, String text_en, String text_fr,
			String text_es, String text_it, String text_nl, String text_sv,
			String text_cs, String text_hu, String text_da) {
		this.number = number;
		this.number_old = number_old;
		this.text_de = text_de;
		this.text_en = text_en;
		this.text_fr = text_fr;
		this.text_es = text_es;
		this.text_it = text_it;
		this.text_nl = text_nl;
		this.text_sv = text_sv;
		this.text_cs = text_cs;
		this.text_hu = text_hu;
		this.text_da = text_da;
	}


	/**
	 * get ident number
	 * @return ident number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * get ident old number
	 * @return ident number
	 */
	public int getNumberOld() {
		return this.number_old;
	}


	/**
	 * get ident text 
	 * @return ident text
	 */
	public String getText(){
		return getText(getNumber());
	}
		
	/**
	 * get ident text by number
	 * @param number ident id
	 * @return ident text
	 */
	public static String getText(int number){
		try{
			String languageCode = Locale.getDefault().getLanguage();
			Log.d("Idents", "getText. Language Code = " + languageCode);
			Idents item = Idents.getItemByCode(number);
			if (item == null) return null;
			
			String defaultText = item.text_en;
			
			if ("en".equalsIgnoreCase(languageCode)){
				return defaultText;
			}
			else if ("de".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_de(), defaultText);
			}
			else if ("fr".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_fr(), defaultText);
			}
			else if ("es".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_es(), defaultText);
			}
			else if ("it".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_it(), defaultText);
			}
			else if ("nl".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_nl(), defaultText);
			}
			else if ("sv".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_sv(), defaultText);
			}
			else if ("cs".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_cs(), defaultText);
			}
			else if ("hu".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_hu(), defaultText);
			}
			else if ("da".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_da(), defaultText);
			}
			else
				return defaultText;
		} catch(Exception ex){
			Log.e("Idents", "getText. Cannot get text for ident id = " + number + ". Error: " + ex);
			return null;
		}
	}

	/**
	 * check locale text and replace it if needed
	 * @param localeText locale text
	 * @param defaultText default text
	 * @return locale or default text
	 */
	private static String checkLocaleText(String localeText, String defaultText){
		if ((localeText == null)||(localeText.equalsIgnoreCase("")))
			return defaultText;
		else return localeText;
		
	}
	
	
	/**
	 * get text in DE
	 * @return the text in DE
	 */
	public String getText_de() {
		return text_de;
	}


	/**
	 * get text in EN
	 * @return the text in EN
	 */
	public String getText_en() {
		return text_en;
	}


	/**
	 * get text in FR
	 * @return the text in FR
	 */
	public String getText_fr() {
		return text_fr;
	}


	/**
	 * get text in ES
	 * @return the text in ES
	 */
	public String getText_es() {
		return text_es;
	}


	/**
	 * get text in IT
	 * @return the text in IT
	 */
	public String getText_it() {
		return text_it;
	}


	/**
	 * get text in NL
	 * @return the text in NL
	 */
	public String getText_nl() {
		return text_nl;
	}


	/**
	 * get text in SV
	 * @return the text in SV
	 */
	public String getText_sv() {
		return text_sv;
	}


	/**
	 * get text in CS
	 * @return the text in CS
	 */
	public String getText_cs() {
		return text_cs;
	}


	/**
	 * get text in HU
	 * @return the text in HU
	 */
	public String getText_hu() {
		return text_hu;
	}


	/**
	 * get text in DA
	 * @return the text in DA
	 */
	public String getText_da() {
		return text_da;
	}
	
	/**
	 * get enum item by number
	 * @param number ident number
	 * @return enum item or null if not found 
	 */
	public static Idents getItemByCode(int number){
		for (Idents i : Idents.values()){
			if (i.getNumber() == number)
				return i;
		}
		return null;
	}
	

}; // enum

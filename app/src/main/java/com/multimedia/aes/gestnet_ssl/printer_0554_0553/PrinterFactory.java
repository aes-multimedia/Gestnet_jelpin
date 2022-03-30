package com.multimedia.aes.gestnet_ssl.printer_0554_0553;


public class PrinterFactory {
	
	private final com.multimedia.aes.gestnet_ssl.printer_0554_0553.PrinterHelper helper;

	
	public PrinterFactory(com.multimedia.aes.gestnet_ssl.printer_0554_0553.PrinterHelper printerHelper) {
		helper = printerHelper;
	}

	private com.multimedia.aes.gestnet_ssl.printer_0554_0553.AblePrinter getAblePrinter(){
		return new AblePrinter(helper);
	}
	private AdvancedPrinter getAdvancedPrinter(){
		return new AdvancedPrinter(helper);
	}
	
	public com.multimedia.aes.gestnet_ssl.printer_0554_0553.Printer getPrinter(String deviceAdress) throws Exception {
		String name =helper.name(deviceAdress);
		Printer printer = getAdvancedPrinter();
		if(name.contains("0554-0543")){
			printer = getAblePrinter();
		}else if(name.contains("0554-0620"))
		{
			printer = getAdvancedPrinter();
		}else
		{
			throw new Exception("No supported Printer");
		}
		return printer;
	}
}

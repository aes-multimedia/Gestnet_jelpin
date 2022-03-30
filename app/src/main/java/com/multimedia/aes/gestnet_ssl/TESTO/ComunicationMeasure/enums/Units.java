/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.enums;

import android.util.Log;

import java.util.Locale;

/**
 * Units definition
 * @author sergey.zaburunov
 */
public enum Units {
// 		         0    1                   2                   3                   4                   5                   6                   7                   8                   9                   10
// 		         ID,  DE,                 EN,                 FR,                 ES,                 IT,                 NL,                 SV,                 CS,                 HU,                 DA
		unit_001(  1, "�C"               ,"�C"               ,"�C"               ,"�C"               ,"�C"               ,"�C"               ,""                 ,"�C"               ,"�C"               ,"�C"       ),
		unit_002(  2, "�F"               ,"�F"               ,"�F"               ,"�F"               ,"�F"               ,"�F"               ,""                 ,"�F"               ,"�F"               ,"�F"       ),
		unit_003(  3, "%"                ,"%"                ,"%"                ,"%"                ,"%"                ,"%"                ,""                 ,"%"                ,"%"                ,"%"        ),
		unit_004(  4, "%"                ,"%"                ,"%"                ,"%"                ,"%"                ,"%"                ,""                 ,"%"                ,"%"                ,"%"        ),
		unit_005(  5, "m/s"              ,"m/s"              ,"m/s"              ,"m/s"              ,"m/s"              ,"m/s"              ,""                 ,"m/s"              ,"m/s"              ,"m/s"      ),
		unit_006(  6, "fpm"              ,"fpm"              ,"fpm"              ,"fpm"              ,"fpm"              ,"fpm"              ,""                 ,"fpm"              ,"fpm"              ,"fpm"      ),
		unit_007(  7, "td�C"             ,"dp�C"             ,"td�C"             ,"dp�C"             ,"dp�C"             ,"td�C"             ,""                 ,"dp�C"             ,"dp�C"             ,"dp�C"     ),
		unit_008(  8, "td�F"             ,"dp�F"             ,"td�F"             ,"dp�F"             ,"dp�F"             ,"td�F"             ,""                 ,"dp�F"             ,"dp�F"             ,"dp�F"     ),
		unit_009(  9, "DeltaT"           ,"DeltaT"           ,"DeltaT"           ,"DeltaT"           ,"DeltaT"           ,"DeltaT"           ,""                 ,"DeltaT"           ,"DeltaT"           ,"DeltaT"   ),
		unit_010( 10, "cm�"              ,"cm�"              ,"cm�"              ,"cm�"              ,"cm�"              ,"cm�"              ,""                 ,"cm�"              ,"cm�"              ,"cm�"      ),
		unit_011( 11, "V"                ,"V"                ,"V"                ,"V"                ,"V"                ,"V"                ,""                 ,"V"                ,"V"                ,"V"        ),
		unit_012( 12, "mV"               ,"mV"               ,"mV"               ,"mV"               ,"mV"               ,"mV"               ,""                 ,"mV"               ,"mV"               ,"mV"       ),
		unit_013( 13, "�V"               ,"�V"               ,"�V"               ,"�V"               ,"�V"               ,"�V"               ,""                 ,"�V"               ,"�V"               ,"�V"       ),
		unit_014( 14, "sec"              ,"sec"              ,"sec"              ,"seg"              ,"sec"              ,"sec"              ,""                 ,"sec"              ,"sec"              ,"sek."     ),
		unit_015( 15, "n"                ,"n"                ,"n"                ,"n"                ,"n"                ,"n"                ,""                 ,"n"                ,"n"                ,"n"        ),
		unit_016( 16, "A"                ,"A"                ,"A"                ,"A"                ,"A"                ,"A"                ,""                 ,"A"                ,"A"                ,"A"        ),
		unit_017( 17, "mA"               ,"mA"               ,"mA"               ,"mA"               ,"mA"               ,"mA"               ,""                 ,"mA"               ,"mA"               ,"mA"       ),
		unit_018( 18, "�A"               ,"�A"               ,"�A"               ,"�A"               ,"�A"               ,"�A"               ,""                 ,"�A"               ,"�A"               ,"�A"       ),
		unit_019( 19, "O"                ,"O"                ,"O"                ,"O"                ,"O"                ,"O"                ,""                 ,"O"                ,"O"                ,"O"        ),
		unit_020( 20, "mO"               ,"mO"               ,"mO"               ,"mO"               ,"mO"               ,"mO"               ,""                 ,"mO"               ,"mO"               ,"mO"       ),
		unit_021( 21, "�O"               ,"�O"               ,"�O"               ,"�O"               ,"�O"               ,"�O"               ,""                 ,"�O"               ,"�O"               ,"�O"       ),
		unit_022( 22, "lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,"lambda"           ,""                 ,"lambda"           ,"lambda"           ,"lambda"   ),
		unit_023( 23, "mbar"             ,"mbar"             ,"mbar"             ,"mbar"             ,"mbar"             ,"mbar"             ,""                 ,"mbar"             ,"mbar"             ,"mbar"     ),
		unit_024( 24, "hPa"              ,"hPa"              ,"hPa"              ,"hPa"              ,"hPa"              ,"hPa"              ,""                 ,"hPa"              ,"hPa"              ,"hPa"      ),
		unit_025( 25, "psi"              ,"psi"              ,"psi"              ,"psi"              ,"psi"              ,"psi"              ,""                 ,"psi"              ,"psi"              ,"psi"      ),
		unit_026( 26, "inch"             ,"inch"             ,"inch"             ,"inch"             ,"inch"             ,"inch"             ,""                 ,"inch"             ,"inch"             ,"tomme"    ),
		unit_027( 27, "% qAP"            ,"% qAP"            ,"% qAP"            ,"% qAP"            ,"% qAP"            ,"% qAP"            ,""                 ,"% qAP"            ,"% qAP"            ,"% qAP"    ),
		unit_028( 28, "% qAS"            ,"% qAS"            ,"% qAS"            ,"% qAS"            ,"% qAS"            ,"% qAS"            ,""                 ,"% qAS"            ,"% qAS"            ,"% qAS"    ),
		unit_033( 33, "m�/h"             ,"m�/h"             ,"m�/h"             ,"m�/h"             ,"m�/h"             ,"m�/h"             ,""                 ,"m�/h"             ,"m�/h"             ,"m�/t"     ),
		unit_034( 34, "ft�/min"          ,"ft�/min"          ,"f�/m"             ,"f�/m"             ,"f�/m"             ,"f�/m"             ,""                 ,"ft�/min"          ,"ft�/p"            ,"ft�/min"  ),
		unit_056( 56, "pH"               ,"pH"               ,"pH"               ,"pH"               ,"pH"               ,"pH"               ,""                 ,"pH"               ,"pH"               ,"pH"       ),
		unit_057( 57, "mm�"              ,"mm�"              ,"mm�"              ,"mm�"              ,"mm�"              ,"mm�"              ,""                 ,"mm�"              ,"mm�"              ,"mm�"      ),
		unit_058( 58, "dpH/dt"           ,"dpH/dt"           ,"dpH/dt"           ,"dpH/dt"           ,"dpH/dt"           ,"dpH/dt"           ,""                 ,"dpH/dt"           ,"dpHdt"            ,"dpH/dt"   ),
		unit_059( 59, "mS"               ,"mS"               ,"mS"               ,"mS"               ,"mS"               ,"mS"               ,""                 ,"mS"               ,"mS"               ,"mS"       ),
		unit_060( 60, "�S"               ,"�S"               ,"�S"               ,"�S"               ,"�S"               ,"�S"               ,""                 ,"�S"               ,"�S"               ,"�S"       ),
		unit_061( 61, "mg/l O2"          ,"mg/l O2"          ,"mg/l O2"          ,"mg/l O2"          ,"mg/l O2"          ,"mg/l O2"          ,""                 ,"mg/l O2"          ,"mg/l "            ,"mg/l O2"  ),
		unit_062( 62, "% SAT"            ,"% SAT"            ,"% SAT"            ,"% SAT"            ,"% SAT"            ,"% SAT"            ,""                 ,"% SAT"            ,"% SAT"            ,"% SAT"    ),
		unit_063( 63, "mg/l"             ,"mg/l"             ,"mg/l"             ,"mg/l"             ,"mg/l"             ,"mg/l"             ,""                 ,"mg/l"             ,"mg/l"             ,"mg/l"     ),
		unit_064( 64, "m"                ,"m"                ,"m"                ,"m"                ,"m"                ,"m"                ,""                 ,"m"                ,"m"                ,"m"        ),
		unit_065( 65, "mm"               ,"mm"               ,"mm"               ,"mm"               ,"mm"               ,"mm"               ,""                 ,"mm"               ,"mm"               ,"mm"       ),
		unit_066( 66, "�m"               ,"�m"               ,"�m"               ,"�m"               ,"�m"               ,"�m"               ,""                 ,"�m"               ,"�m"               ,"�m"       ),
		unit_069( 69, "min"              ,"min"              ,"min"              ,"min"              ,"min"              ,"min"              ,""                 ,"min"              ,"min"              ,"min"      ),
		unit_070( 70, "h"                ,"h"                ,"h"                ,"h"                ,"h"                ,"h"                ,""                 ,"h"                ,"h"                ,"t"        ),
		unit_073( 73, "l/min"            ,"l/min"            ,"l/min"            ,"l/min"            ,"l/min"            ,"l/min"            ,""                 ,"l/min"            ,"l/min"            ,"l/min"    ),
		unit_074( 74, "t/T"              ,"t/D"              ,"t/j"              ,"t/D"              ,"t/D"              ,"t/d"              ,""                 ,"t/D"              ,"t/D"              ,"t/D"      ),
		unit_075( 75, "t/J"              ,"t/y"              ,"t/a"              ,"t/y"              ,"t/y"              ,"t/jr"             ,""                 ,"t/y"              ,"t/y"              ,"t/y"      ),
		unit_077( 77, "m�/min"           ,"m�/min"           ,"m�/m"             ,"m�/m"             ,"m�/m"             ,"m�/m"             ,""                 ,"m�/min"           ,"m�/p"             ,"m�/min"   ),
		unit_078( 78, "Torr"             ,"Torr"             ,"Torr"             ,"Torr"             ,"Torr"             ,"Torr"             ,""                 ,"Torr"             ,"Torr"             ,"Torr"     ),
		unit_079( 79, "inW"              ,"inH2O"            ,"inH2O"            ,"inW"              ,"inW"              ,"inW"              ,""                 ,"inW"              ,"inW"              ,"inW"      ),
		unit_080( 80, "cm"               ,"cm"               ,"cm"               ,"cm"               ,"cm"               ,"cm"               ,""                 ,"cm"               ,"cm"               ,"cm"       ),
		unit_081( 81, "ft"               ,"ft"               ,"ft"               ,"ft"               ,"ft"               ,"ft"               ,""                 ,"ft"               ,"ft"               ,"ft"       ),
		unit_082( 82, "mmWS"             ,"mmW"              ,"mmH2O"            ,"mmW"              ,"mmW"              ,"mmWS"             ,""                 ,"mmW"              ,"mmW"              ,"mmW"      ),
		unit_083( 83, "d"                ,"d"                ,"j"                ,"d"                ,"d"                ,"d"                ,""                 ,"d"                ,"d"                ,"d"        ),
		unit_084( 84, "dT"               ,"dT"               ,"dT"               ,"dT"               ,"dT"               ,"dT"               ,""                 ,"dT"               ,"dT"               ,"dT"       ),
		unit_085( 85, "kg"               ,"kg"               ,"kg"               ,"kg"               ,"kg"               ,"kg"               ,""                 ,"kg"               ,"kg"               ,"kg"       ),
		unit_086( 86, "m�"               ,"m�"               ,"m�"               ,"m�"               ,"m�"               ,"m�"               ,""                 ,"m�"               ,"m�"               ,"m�"       ),
		unit_087( 87, "m�"               ,"m�"               ,"m�"               ,"m�"               ,"m�"               ,"m�"               ,""                 ,"m�"               ,"m�"               ,"m�"       ),
		unit_088( 88, "lt/J"             ,"lt/y"             ,"lt/a"             ,"lt/y"             ,"lt/y"             ,"lt/jr"            ,""                 ,"lt/y"             ,"lt/y"             ,"lt/y"     ),
		unit_089( 89, "lb/h"             ,"lb/h"             ,"lb/h"             ,"lb/h"             ,"lb/h"             ,"lb/h"             ,""                 ,"lb/h"             ,"lb/h"             ,"lb/t"     ),
		unit_090( 90, "J/g"              ,"J/g"              ,"J/g"              ,"J/g"              ,"J/g"              ,"J/g"              ,""                 ,"J/g"              ,"J/g"              ,"J/g"      ),
		unit_091( 91, "m�/s"             ,"m�/s"             ,"m�/s"             ,"m�/s"             ,"m�/s"             ,"m�/s"             ,""                 ,"m�/s"             ,"m�/s"             ,"m�/sek"   ),
		unit_092( 92, "l/s"              ,"l/s"              ,"l/s"              ,"l/s"              ,"l/s"              ,"l/s"              ,""                 ,"l/s"              ,"l/s"              ,"l/sek"    ),
		unit_093( 93, "inHg"             ,"inHg"             ,"inHg"             ,"inHg"             ,"inHg"             ,"inHg"             ,""                 ,"inHg"             ,"inHg"             ,"inHg"     ),
		unit_094( 94, "kPa"              ,"kPa"              ,"kPa"              ,"kPa"              ,"kPa"              ,"kPa"              ,""                 ,"kPa"              ,"kPa"              ,"kPa"      ),
		unit_095( 95, "1/m."             ,"1/m."             ,"1/m."             ,"1/m."             ,"1/m."             ,"1/m."             ,""                 ,"1/m."             ,"1/m."             ,"1/m."     ),
		unit_096( 96, "m�/h"             ,"m�/h"             ,"m�/h"             ,"m�/h"             ,"m�/h"             ,"m�/h"             ,""                 ,"m�/h"             ,"m�/h"             ,"m�/t"     ),
		unit_097( 97, "ft�/min"          ,"ft�/min"          ,"f�/m"             ,"f�/m"             ,"f�/m"             ,"f�/m"             ,""                 ,"ft�/min"          ,"ft�/p"            ,"ft�/min"  ),
		unit_098( 98, "m�/min"           ,"m�/min"           ,"m�/m"             ,"m�/m"             ,"m�/m"             ,"m�/m"             ,""                 ,"m�/min"           ,"m�/p"             ,"m�/min"   ),
		unit_100(100, "m�/s"             ,"m�/s"             ,"m�/s"             ,"m�/s"             ,"m�/s"             ,"m�/s"             ,""                 ,"m�/s"             ,"m�/s"             ,"m�/sek"   ),
		unit_101(101, "L/s"              ,"L/s"              ,"L/s"              ,"L/s"              ,"L/s"              ,"L/s"              ,""                 ,"L/s"              ,"L/s"              ,"L/sek"    ),
		unit_102(102, "Lux"              ,"Lux"              ,"Lux"              ,"Lux"              ,"Lux"              ,"Lux"              ,""                 ,"Lux"              ,"Lux"              ,"Lux"      ),
		unit_103(103, "Candela"          ,"Candela"          ,"Candela"          ,"Candela"          ,"Candela"          ,"Candela"          ,""                 ,"Candela"          ,"Cand."            ,"Candela"  ),
		unit_104(104, "aw"               ,"aw"               ,"aw"               ,"aw"               ,"aw"               ,"aw"               ,""                 ,"aw"               ,"aw"               ,"aw"       ),
		unit_105(105, "hPaB"             ,"mbar"             ,"hPaB"             ,"mbar"             ,"mbar"             ,"hPaB"             ,""                 ,"mbar"             ,"mbar"             ,"mbar"     ),
		unit_106(106, "ft�/s"            ,"ft�/s"            ,"f�/s"             ,"f�/s"             ,"f�/s"             ,"f�/s"             ,""                 ,"ft�/s"            ,"ft�/s"            ,"ft�/sek"  ),
		unit_107(107, "ft�/h"            ,"ft�/h"            ,"f�/h"             ,"f�/h"             ,"f�/h"             ,"f�/h"             ,""                 ,"ft�/h"            ,"ft�/h"            ,"ft�/t"    ),
		unit_108(108, "ft�/d"            ,"ft�/d"            ,"f�/j"             ,"f�/d"             ,"f�/d"             ,"f�/d"             ,""                 ,"ft�/d"            ,"ft�/d"            ,"ft�/d"    ),
		unit_109(109, "ft�/y"            ,"ft�/y"            ,"f�/a"             ,"f�/y"             ,"f�/y"             ,"f�/jr"            ,""                 ,"ft�/y"            ,"ft�/y"            ,"ft�/y"    ),
		unit_110(110, "g/hp"             ,"g/hp"             ,"g/hp"             ,"g/hp"             ,"g/hp"             ,"g/hp"             ,""                 ,"g/hp"             ,"g/hp"             ,"g/hp"     ),
		unit_111(111, "ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,""                 ,"ft�"              ,"ft�"              ,"ft�"      ),
		unit_112(112, "in�"              ,"in�"              ,"in�"              ,"in�"              ,"in�"              ,"in�"              ,""                 ,"in�"              ,"in�"              ,"in�"      ),
		unit_113(113, "gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,""                 ,"gr/ft�"           ,"grft�"            ,"gr/ft�"   ),
		unit_114(114, "gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,"gr/ft�"           ,""                 ,"gr/ft�"           ,"grft�"            ,"gr/ft�"   ),
		unit_115(115, "p/ft�"            ,"p/ft�"            ,"p/ft�"            ,"p/ft�"            ,"p/ft�"            ,"p/ft�"            ,""                 ,"p/ft�"            ,"p/ft�"            ,"p/ft�"    ),
		unit_116(116, "PS"               ,"PS"               ,"PS"               ,"PS"               ,"PS"               ,"PS"               ,""                 ,"PS"               ,"PS"               ,"PS"       ),
		unit_117(117, "kW"               ,"kW"               ,"kW"               ,"kW"               ,"kW"               ,"kW"               ,""                 ,"kW"               ,"kW"               ,"kW"       ),
		unit_118(118, "l"                ,"l"                ,"l"                ,"l"                ,"l"                ,"l"                ,""                 ,"l"                ,"l"                ,"l"        ),
		unit_128(128, "kHz"              ,"KHz"              ,"kHz"              ,"KHz"              ,"KHz"              ,"kHz"              ,""                 ,"KHz"              ,"KHz"              ,"KHz"      ),
		unit_129(129, "g/m�"             ,"g/m�"             ,"g/m�"             ,"g/m�"             ,"g/m�"             ,"g/m�"             ,""                 ,"g/m�"             ,"g/m�"             ,"g/m�"     ),
		unit_130(130, "%"                ,"%"                ,"%"                ,"%"                ,"%"                ,"%"                ,""                 ,"%"                ,"%"                ,"%"        ),
		unit_131(131, "ppm"              ,"ppm"              ,"ppm"              ,"ppm"              ,"ppm"              ,"ppm"              ,""                 ,"ppm"              ,"ppm"              ,"ppm"      ),
		unit_132(132, "mg"               ,"mg"               ,"mg"               ,"mg"               ,"mg"               ,"mg"               ,""                 ,"mg"               ,"mg"               ,"mg"       ),
		unit_133(133, "bar"              ,"bar"              ,"bar"              ,"bar"              ,"bar"              ,"bar"              ,""                 ,"bar"              ,"bar"              ,"bar"      ),
		unit_134(134, "g/kg"             ,"g/kg"             ,"g/kg"             ,"g/kg"             ,"g/kg"             ,"g/kg"             ,""                 ,"g/kg"             ,"g/kg"             ,"g/kg"     ),
		unit_135(135, "g/GJ"             ,"g/GJ"             ,"g/GJ"             ,"g/GJ"             ,"g/GJ"             ,"g/GJ"             ,""                 ,"g/GJ"             ,"g/GJ"             ,"g/GJ"     ),
		unit_136(136, "mg/kwh"           ,"mg/kwh"           ,"mg/kwh"           ,"mg/kwh"           ,"mg/kwh"           ,"mg/kwh"           ,"mg/kwh"           ,"mg/kwh"           ,"mgkwh"            ,"mg/kwh"   ),
		unit_137(137, "Pa"               ,"Pa"               ,"Pa"               ,"Pa"               ,"Pa"               ,"Pa"               ,""                 ,"Pa"               ,"Pa"               ,"Pa"       ),
		unit_139(139, "mg/m�"            ,"mg/m�"            ,"mg/m�"            ,"mg/m�"            ,"mg/m�"            ,"mg/m�"            ,""                 ,"mg/m�"            ,"mg/m�"            ,"mg/m�"    ),
		unit_155(155, "K"                ,"K"                ,"K"                ,"K"                ,"K"                ,"K"                ,""                 ,"K"                ,"K"                ,"K"        ),
		unit_166(166, "#BTU"             ,"#BTU"             ,"#BTU"             ,"#BTU"             ,"#BTU"             ,"#BTU"             ,""                 ,"#BTU"             ,"#BTU"             ,"#BTU"     ),
		unit_167(167, "ppm"              ,"ppm"              ,"ppm"              ,"ppm"              ,"ppm"              ,"ppm"              ,""                 ,"ppm"              ,"ppm"              ,"ppm"      ),
		unit_168(168, "mg/m�"            ,"mg/m�"            ,"mg/m�"            ,"mg/m�"            ,"mg/m�"            ,"mg/m�"            ,""                 ,"mg/m�"            ,"mg/m�"            ,"mg/m�"    ),
		unit_169(169, "#BTU"             ,"#BTU"             ,"#BTU"             ,"#BTU"             ,"#BTU"             ,"#BTU"             ,""                 ,"#BTU"             ,"#BTU"             ,"#BTU"     ),
		unit_186(186, "m�/min"           ,"m�/min"           ,"m�/m"             ,"m�/m"             ,"m�/m"             ,"m�/m"             ,""                 ,"m�/min"           ,"m�/p"             ,"m�/min"   ),
		unit_188(188, "m�/T"             ,"m�/d"             ,"m�/j"             ,"m�/d"             ,"m�/d"             ,"m�/d"             ,""                 ,"m�/d"             ,"m�/d"             ,"m�/d"     ),
		unit_189(189, "m�/J"             ,"m�/J"             ,"m�/a"             ,"m�/J"             ,"m�/J"             ,"m�/jr"            ,""                 ,"m�/J"             ,"m�/J"             ,"m�/J"     ),
		unit_190(190, "t/h"              ,"t/h"              ,"t/h"              ,"t/h"              ,"t/h"              ,"t/h"              ,""                 ,"t/h"              ,"t/h"              ,"t/h"      ),
		unit_191(191, "kg/h"             ,"kg/h"             ,"kg/h"             ,"kg/h"             ,"kg/h"             ,"kg/h"             ,""                 ,"kg/h"             ,"kg/h"             ,"kg/h"     ),
		unit_192(192, "kg/T"             ,"kg/d"             ,"kg/j"             ,"kg/d"             ,"kg/d"             ,"kg/d"             ,""                 ,"kg/d"             ,"kg/d"             ,"kg/d"     ),
		unit_194(194, "kO"               ,"kO"               ,"kO"               ,"kO"               ,"kO"               ,"kO"               ,""                 ,"kO"               ,"kO"               ,"kO"       ),
		unit_195(195, "�lder"            ,"Oil deposits"     ,"R�s.fioul"        ,"Oil der"          ,"Oleoder."         ,"Olie der"         ,""                 ,"Oil der"          ,"Olaj "            ,"Oliederv."),
		unit_196(196, "kg/m�"            ,"kg/m�"            ,"kg/m�"            ,"kg/m�"            ,"kg/m�"            ,"kg/m�"            ,""                 ,"kg/m�"            ,"kg/m�"            ,"kg/m�"    ),
		unit_197(197, "g/m�"             ,"g/m�"             ,"g/m�"             ,"g/m�"             ,"g/m�"             ,"g/m�"             ,""                 ,"g/m�"             ,"g/m�"             ,"g/m�"     ),
		unit_198(198, "M"                ,"M"                ,"M"                ,"M"                ,"M"                ,"M"                ,""                 ,"M"                ,"M"                ,"M"        ),
		unit_199(199, "y"                ,"y"                ,"a"                ,"y"                ,"y"                ,"jr"               ,""                 ,"y"                ,"y"                ,"y"        ),
		unit_206(206, "gal/h"            ,"gal/h"            ,"USgal/h"          ,"USgal/h"          ,"USgal/h"          ,"USgal/h"          ,"gal/h"            ,"gal/h"            ,"gal/h"            ,"gal/h"    ),
		unit_207(207, "gal"              ,"gal"              ,"USgal"            ,"USgal"            ,"USgal"            ,"USgal"            ,"gal"              ,"gal"              ,"gal"              ,"gal"      ),
		unit_208(208, "l/h"              ,"l/h"              ,"l/h"              ,"l/h"              ,"l/h"              ,"l/h"              ,"l/h"              ,"l/h"              ,"l/h"              ,"l/t"      ),
		unit_209(209, "ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"              ,"ft�"      ),
		unit_210(210, "fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"            ,"fl.oz"    ),
		unit_211(211, "foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"            ,"foz/h"    ),
		unit_099( 99, ""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""         );
                
	
	/**
	 * unit number
	 */
	private final int number;
	
	/**
	 * unit text in DE
	 */
	private final String text_de;

	/**
	 * unit text in EN
	 */
	private final String text_en;

	/**
	 * unit text in FR
	 */
	private final String text_fr;

	/**
	 * unit text in ES
	 */
	private final String text_es;

	/**
	 * unit text in IT
	 */
	private final String text_it;

	/**
	 * unit text in NL
	 */
	private final String text_nl;

	/**
	 * unit text in SV
	 */
	private final String text_sv;

	/**
	 * unit text in CS
	 */
	private final String text_cs;

	/**
	 * unit text in HU
	 */
	private final String text_hu;

	/**
	 * unit text in DA
	 */
	private final String text_da;

	
	
	/**
	 * Constructor
	 * @param number unit number
	 * @param text_de unit text in DE
	 * @param text_en unit text in EN
	 * @param text_fr unit text in FR
	 * @param text_es unit text in ES
	 * @param text_it unit text in IT
	 * @param text_nl unit text in NL
	 * @param text_sv unit text in SV
	 * @param text_cs unit text in CS
	 * @param text_hu unit text in HU
	 * @param text_da unit text in DA
	 */
	private Units(int number, String text_de, String text_en, String text_fr,
			String text_es, String text_it, String text_nl, String text_sv,
			String text_cs, String text_hu, String text_da) {
		this.number = number;
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
	 * get unit number
	 * @return unit number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * get unit text 
	 * @return unit text
	 */
	public String getText(){
		return getText(getNumber());
	}


	/**
	 * get unit text by number
	 * @param number unit id
	 * @return unit text
	 */
	public static String getText(int number){
		try{
			String languageCode = Locale.getDefault().getLanguage();
			Log.d("Units", "getText. Language Code = " + languageCode);
			Units item = Units.getItemByCode(number);
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
			Log.e("Units", "getText. Cannot get text for unit id = " + number + ". Error: " + ex);
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
	 * @param number unit number
	 * @return enum item or null if not found 
	 */
	public static Units getItemByCode(int number){
		for (Units i : Units.values()){
			if (i.getNumber() == number)
				return i;
		}
		return null;
	}
	

}; // enum

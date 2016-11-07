/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.enums;

import android.util.Log;

import java.util.Locale;

/**
 * Fuels definition
 * @author sergey.zaburunov
 */
public enum Fuels {
// 		       0      1                   2                   3                   4                   5                   6                   7                   8                   9                   10
// 		       ID,    DE,                 EN,                 FR,                 ES,                 IT,                 NL,                 SV,                 CS,                 HU,                 DA
	fuel_00099(   99, "Pr�fgas"          ,"Test Gas"         ,"Gaz �talon"       ,"Gas patr�n"       ,"Gas di prova"     ,"Testgas"          ,"Pr�fgas"          ,"Zku�ebn� plyn"    ,"Tesztg�z"         ,"Test gas"         ),
	fuel_00100(  100, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_00101(  101, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_00102(  102, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_00103(  103, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_00104(  104, "Erdgas"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Natural"      ,"Gas Naturale"     ,"Aardgas"          ,"Erdgas"           ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_00105(  105, "Erdgas "         ,"Natural gas"      ,"Gaz naturel "    ,"Gas Natural"      ,"Erdgas "         ,"Aardgas Ho"       ,"Erdgas "         ,"Zemn� plyn "      ,"F�ldg�z"          ,"N-gas "               ),
	fuel_00106(  106, "Erdgas H"         ,"Natural gas"      ,"Gaz naturel H"    ,"Gas Natural"      ,"Erdgas H"         ,"Aardgas Hb"       ,"Erdgas H"         ,"Zemn� plyn "      ,"F�ldg�z"          ,"Naturgas"         ),
	fuel_00107(  107, "Fl�ssiggas"       ,"LPG"              ,"Gaz liqu�fi�"     ,"Gas l�quido"      ,"Gas liquido"      ,"Vloeibaargas"     ,"Fl�ssiggas"       ,"Propan"           ,"LPG"              ,"Flydende gas"     ),
	fuel_00108(  108, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_00109(  109, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_00110(  110, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_00111(  111, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_00112(  112, "Steinkohle"       ,"Coal"             ,"Houille"          ,"Hulla"            ,"Carbone"          ,"Steenkool"        ,"Steinkohle"       ,"Cern� uhl�"       ,"Kosz�n"           ,"Stenkul"          ),
	fuel_00113(  113, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_00114(  114, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_00115(  115, "Torf"             ,"Peat"             ,"Tourbe"           ,"Turba"            ,"Torba"            ,"Turf"             ,"Torf"             ,"Ra�elina"         ,"Tozeg"            ,"T�rv"             ),
	fuel_00116(  116, "Holz 15%w"        ,"Wood 15%w"        ,"Bois 15%eau"      ,"Madera 15%"       ,"Legno 15%w"       ,"Hout 15%w"        ,"Holz 15%w"        ,"Drevo 15%"        ,"Fa 15%w"          ,"Tr� 15%w"         ),
	fuel_00117(  117, "Holz 30%w"        ,"Wood 30%w"        ,"Bois 30%eau"      ,"Madera 30%"       ,"Legno 30%w"       ,"Hout 30%w"        ,"Holz 30%w"        ,"Drevo 30%"        ,"Fa 30%w"          ,"Tr� 30%w"         ),
	fuel_00118(  118, "Holz 45%w"        ,"Wood 45%w"        ,"Bois 45%eau"      ,"Madera 45%"       ,"Legno 45%w"       ,"Hout 45%w"        ,"Holz 45%w"        ,"Drevo 40%"        ,"Fa 45%w"          ,"Tr� 45%w"         ),
	fuel_00119(  119, "Holz 60%w"        ,"Wood 60%w"        ,"Bois 60%eau"      ,"Madera 60%"       ,"Legno 60%w"       ,"Hout 60%w"        ,"Holz 60%w"        ,"Drevo 60%"        ,"Fa 60%w"          ,"Tr� 60%w"         ),
	fuel_00120(  120, "Erdgas (russisch)","Nature Gas (Russia)","Gaz naturel russe","Gas Natural (Rusia)","Erdgas (russisch)","Aardgas (Rus)"    ,"Erdgas (russisch)","Zemn� plyn (rusk�)","F�ldg�z (orosz)"  ,"N-gas (russisk)"),
	fuel_00121(  121, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_00122(  122, "Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Bagazo"           ,"Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Vylisovan� cukrov� trtina","Pr�selt cukorn�d" ,"Bagasse"  ),
	fuel_00123(  123, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_00124(  124, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_00125(  125, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_00126(  126, "Biomasse"         ,"Biomass"          ,"Biomasse"         ,"Biomasa"          ,"Biomass"          ,"Biomass"          ,"Biomass"          ,"Biomasa"          ,"Biomassza"        ,"Biomass"          ),
	fuel_00127(  127, "Petroleum"        ,"Kerosene"         ,"K�ros�ne"         ,"Queroseno"        ,"Kerosene"         ,"Kerosine"         ,"Kerosene"         ,"Kerosin"          ,"Kerozin"          ,"Kerosene"         ),
	fuel_00128(  128, "Scheitholz"       ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00129(  129, "Hackschnitzel"    ,"Woodchips"        ,"Copeau"           ,"Virutas"          ,"Trucioli legno"   ,"Houtsnippers"     ,"Tr�flis"          ,""                 ,"Faapr�t�k"        ,""                 ),
	fuel_00130(  130, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_00131(  131, "Holzbriketts"     ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00132(  132, "Steinkohle"       ,"Black coal"       ,"Houille"          ,"Hulla"            ,"Carbone fossile"  ,"Steenkool"        ,"Steinkohle"       ,"Cern� uhl�"       ,"Kosz�n"           ,"Sort kul"         ),
	fuel_00133(  133, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_00134(  134, "Brenntorf"        ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00135(  135, "Holzkohle"        ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00136(  136, "Reisig"           ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00137(  137, "Zapfen"           ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00138(  138, "S�gesp�ne"        ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00139(  139, "behandeltes Holz" ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00140(  140, "Spanplatten"      ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00141(  141, "Stroh"            ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00142(  142, "Getreide"         ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_00143(  143, "NaWaRo"           ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ,""                 ),
	fuel_02000( 2000, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_02001( 2001, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_02002( 2002, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_02003( 2003, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_02004( 2004, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_02005( 2005, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_02006( 2006, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_02007( 2007, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_02008( 2008, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_02009( 2009, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_02010( 2010, "Petroleum"        ,"Kerosene"         ,"K�ros�ne"         ,"Queroseno"        ,"Kerosene"         ,"Kerosine"         ,"Kerosene"         ,"Kerosin"          ,"Kerozin"          ,"Kerosene"         ),
	fuel_02011( 2011, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_02012( 2012, "Holz"             ,"Wood"             ,"Bois"             ,"Madera"           ,"Legno"            ,"Hout"             ,"Wood"             ,"Drevo"            ,"Fa"               ,"Tr�"              ),
	fuel_02013( 2013, "Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Bagazo"           ,"Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Vylisovan� cukrov� trtina","Pr�selt cukorn�d" ,"Bagasse"  ),
	fuel_02014( 2014, "Fl�ssiggas"       ,"LPG"              ,"Gaz liqu�fi�"     ,"Gas l�quido"      ,"Gas liquido"      ,"Vloeibaargas"     ,"Fl�ssiggas"       ,"Propan"           ,"LPG"              ,"Flydende gas"     ),
	fuel_02015( 2015, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_03000( 3000, "Heiz�l-EL A"      ,"Gasoleo A"        ,"Fioul dom A"      ,"Gasoleo A"        ,"Gasoleo A"        ,"Gasolie A"        ,"Gasoleo A"        ,"Topn� olej A"     ,"D�zel A"          ,"Gasolie A"        ),
	fuel_03001( 3001, "Heiz�l-EL C"      ,"Gasoleo C"        ,"Fioul dom C"      ,"Gasoleo C"        ,"Gasoleo C"        ,"Gasolie C"        ,"Gasoleo C"        ,"Topn� olej C"     ,"D�zel C"          ,"Gasolie C"        ),
	fuel_03002( 3002, "Heiz�l-S n1"      ,"F-Oleo n1"        ,"Fioul L n1"       ,"F-Oleo n1"        ,"F-Oleo n1"        ,"Stookolie N1"     ,"F-Oleo n1"        ,"F-Oleo n1"        ,"F-Olaj n1"        ,"Fyringsolie HS1"  ),
	fuel_03003( 3003, "Heiz�l-S n2"      ,"F-Oleo n2"        ,"Fioul L n2"       ,"F-Oleo n2"        ,"F-Oleo n2"        ,"Stookolie N2"     ,"F-Oleo n2"        ,"F-Oleo n2"        ,"F-Olaj n2"        ,"F-Olie n2"        ),
	fuel_03004( 3004, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_03005( 3005, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_03006( 3006, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_03007( 3007, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_03008( 3008, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_03009( 3009, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_03010( 3010, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_03011( 3011, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_03012( 3012, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_03013( 3013, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_03014( 3014, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_03015( 3015, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_03016( 3016, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_03017( 3017, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_03018( 3018, "Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Bagazo"           ,"Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Vylisovan� cukrov� trtina","Pr�selt cukorn�d" ,"Bagasse"  ),
	fuel_03019( 3019, "Holz"             ,"Wood"             ,"Bois"             ,"Madera"           ,"Legno"            ,"Hout"             ,"Wood"             ,"Drevo"            ,"Fa"               ,"Tr�"              ),
	fuel_03020( 3020, "Fl�ssiggas"       ,"LPG"              ,"Gaz liqu�fi�"     ,"Gas l�quido"      ,"Gas liquido"      ,"Vloeibaargas"     ,"Fl�ssiggas"       ,"Propan"           ,"LPG"              ,"Flydende gas"     ),
	fuel_03021( 3021, "Biomasse 20%"     ,"Biomass 20%"      ,"Biomasse 20%"     ,"Biomasa 20%"      ,"Biomass 20%"      ,"Biomass 20%"      ,"Biomass 20%"      ,"Biomasa 20%"      ,"Biomassza 20%"    ,"Biomass 20%"      ),
	fuel_03022( 3022, "Bioheat 5"        ,"Bioheat 5"        ,"Bioheat 5"        ,"Biomasa 5"        ,"Bioheat 5"        ,"Biodiesel 5"      ,"Bioheat 5"        ,"Bionafta 5"       ,"Bioheat 5"        ,"Biofuel 5"        ),
	fuel_03023( 3023, ""                 ,"Ethanol"          ,"Ethanol"          ,"Etanol"           ,"Ethanol"          ,""                 ,""                 ,"Ethanol"          ,""                 ,""                 ),
	fuel_03024( 3024, ""                 ,"Methanol"         ,"M�thanol"         ,"Metanol"          ,"Methanol"         ,""                 ,""                 ,"Methanol"         ,""                 ,""                 ),
	fuel_04000( 4000, "Erdgas Hb"        ,"Aardgas Hb"       ,"Gaz naturel Hb"   ,"Aardgas Hb"       ,"Aardgas Hb"       ,"Aardgas Hb"       ,"Aardgas Hb"       ,"Zemn� plyn H"     ,"F�ldg�z Hb"       ,"Aardgas Hb (Hollandsk)"),
	fuel_04001( 4001, "Erdgas Ho"        ,"Aardgas Ho"       ,"Gaz naturel Ho"   ,"Aardgas Ho"       ,"Aardgas Ho"       ,"Aardgas Ho"       ,"Aardgas Ho"       ,"Zemn� plyn "     ,"F�ldg�z Ho"       ,"Aardgas Ho (Hollandsk)"),
	fuel_04002( 4002, "Propan Hb"        ,"Propaan Hb"       ,"Propane Hb"       ,"Propaan Hb"       ,"Propaan Hb"       ,"Propaan Hb"       ,"Propaan Hb"       ,"Propan Hb"        ,"Prop�n Hb"        ,"Propan Hb"        ),
	fuel_04003( 4003, "Propan Ho"        ,"Propaan Ho"       ,"Propane Ho"       ,"Propaan Ho"       ,"Propaan Ho"       ,"Propaan Ho"       ,"Propaan Ho"       ,"Propan Ho"        ,"Prop�n Ho"        ,"Propan Ho"        ),
	fuel_04004( 4004, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_04005( 4005, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_04006( 4006, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_04007( 4007, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_04008( 4008, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_04009( 4009, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_04010( 4010, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_04011( 4011, "Holz 15%w"        ,"Wood 15%w"        ,"Bois 15%eau"      ,"Madera 15%"       ,"Legno 15%w"       ,"Hout 15%w"        ,"Holz 15%w"        ,"Drevo 15%"        ,"Fa 15%w"          ,"Tr� 15%w"         ),
	fuel_04012( 4012, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_04013( 4013, "G20"              ,"G20"              ,"G20"              ,"G20"              ,"G20"              ,"G20"              ,""                 ,"G20"              ,"G20"              ,"G20"              ),
	fuel_04014( 4014, "G25"              ,"G25"              ,"G25"              ,"G25"              ,"G25"              ,"G25"              ,""                 ,"G25"              ,"G25"              ,"G25"              ),
	fuel_04015( 4015, "G30"              ,"G30"              ,"G30"              ,"G30"              ,"G30"              ,"G30"              ,""                 ,"G30"              ,"G30"              ,"G30"              ),
	fuel_05000( 5000, "13A"              ,"13A"              ,"13A"              ,"13A"              ,"13A"              ,"13A"              ,"13A"              ,"13A"              ,"13A"              ,"13A"              ),
	fuel_05001( 5001, "6C"               ,"6C"               ,"6C"               ,"6C"               ,"6C"               ,"6C"               ,"6C"               ,"6C"               ,"6C"               ,"6C"               ),
	fuel_05002( 5002, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_05003( 5003, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_05004( 5004, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_05005( 5005, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_05006( 5006, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_05007( 5007, "Petroleum"        ,"Kerosene"         ,"K�ros�ne"         ,"Queroseno"        ,"Kerosene"         ,"Kerosine"         ,"Kerosene"         ,"Kerosin"          ,"Kerozin"          ,"Kerosene"         ),
	fuel_05008( 5008, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_05009( 5009, "Heavy Oil C"      ,"Heavy Oil C"      ,"Fioul lourd C"    ,"Comb. Pesado C"   ,"Olio combustibile C","Zware olie C"     ,"Heavy Oil C"      ,"Te�k� olej C"     ,"Neh�z futoolaj C" ,"Sv�r olie C"    ),
	fuel_05010( 5010, "Steinkohle"       ,"Coal"             ,"Houille"          ,"Hulla"            ,"Carbone"          ,"Steenkool"        ,"Steinkohle"       ,"Cern� uhl�"       ,"Kosz�n"           ,"Stenkul"          ),
	fuel_05011( 5011, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_05012( 5012, "12A"              ,"12A"              ,"12A"              ,"12A"              ,"12A"              ,"12A"              ,"12A"              ,"12A"              ,"12A"              ,"12A"              ),
	fuel_05013( 5013, "6A"               ,"6A"               ,"6A"               ,"6A"               ,"6A"               ,"6A"               ,"6A"               ,"6A"               ,"6A"               ,"6A"               ),
	fuel_05014( 5014, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_05015( 5015, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_05016( 5016, "5C"               ,"5C"               ,"5C"               ,"5C"               ,"5C"               ,"5C"               ,"5C"               ,"5C"               ,"5C"               ,"5C"               ),
	fuel_05017( 5017, "N1"               ,"N1"               ,"N1"               ,"N1"               ,"N1"               ,"N1"               ,"N1"               ,"N1"               ,"N1"               ,"N1"               ),
	fuel_05018( 5018, "N2"               ,"N2"               ,"N2"               ,"N2"               ,"N2"               ,"N2"               ,"N2"               ,"N2"               ,"N2"               ,"N2"               ),
	fuel_05019( 5019, ""                 ,"�than"            ,"Ethane"           ,"�than"            ,"�than"            ,""                 ,""                 ,"�than"            ,""                 ,""                 ),
	fuel_05020( 5020, ""                 ,"Masut"            ,"Mazout"           ,"Masut"            ,"Masut"            ,""                 ,""                 ,"Mazut"            ,""                 ,""                 ),
	fuel_05021( 5021, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_06000( 6000, "russ. Erdgas"     ,"Gas Nat."         ,"Gas Nat."         ,"Gas Nat."         ,"Gas Nat."         ,"Biogas"           ,"Gas Nat."         ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-Gas"            ),
	fuel_06001( 6001, "GPL (mix)"        ,"GPL (mix)"        ,"G.P.L"            ,"G.P.L"            ,"GPL (misto)"      ,"G.P.L"            ,"G.P.L"            ,"G.P.L"            ,"G.P.L"            ,"G.P.L"            ),
	fuel_06002( 6002, "Heiz�l-EL"        ,"GasOlio"          ,"Fioul dom"        ,"Gasoleo"          ,"Gasolio"          ,"Gasolie"          ,"GasOlio"          ,"GasOlio"          ,"D�zel"            ,"Fyringsolie"      ),
	fuel_06003( 6003, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_06004( 6004, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_06005( 6005, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_06006( 6006, "GPL (Propan)"     ,"GPL (propan)"     ,"GPL (Propane)"    ,"C3H8"             ,"GPL (propano)"    ,"Propaan"          ,"C3H8"             ,"C3H8"             ,"Prop�n"           ,"C3H8"             ),
	fuel_06007( 6007, "GPL (Butan)"      ,"GPL (butan)"      ,"GPL (Butane)"     ,"Gas l�quido"      ,"GPL (butano)"     ,"Vloeibaargas"     ,"Gas Liquid"       ,"Gas Liquid"       ,"But�n"            ,"Flydende gas"     ),
	fuel_06008( 6008, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_06009( 6009, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_06010( 6010, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_06011( 6011, "Braunkohle rhein.","Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Lignite"          ,"Lignite"          ,"Lignit"           ,"Lignite"          ),
	fuel_06012( 6012, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_06013( 6013, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_06014( 6014, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_06015( 6015, "Holz 15%w"        ,"Wood 15%w"        ,"Bois 15%eau"      ,"Madera 15%"       ,"Legno 15%w"       ,"Hout 15%w"        ,"Holz 15%w"        ,"Drevo 15%"        ,"Fa 15%w"          ,"Tr� 15%w"         ),
	fuel_06016( 6016, "Erdgas"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Aardgas"          ,"Erdgas"           ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_06017( 6017, "Biomasse 30%"     ,"Biomass 30%"      ,"Biomasse 30%"     ,"Biomasa 30%"      ,"Biomass 30%"      ,"Biomass 30%"      ,"Biomass 30%"      ,"Biomasa 30%"      ,"Biomassza 30%"    ,"Biomass 30%"      ),
	fuel_06018( 6018, "Holz 30%w"        ,"Wood 30%w"        ,"Bois 30%eau"      ,"Madera 30%"       ,"Legno 30%w"       ,"Hout 30%w"        ,"Holz 30%w"        ,"Drevo 30%"        ,"Fa 30%w"          ,"Tr� 30%w"         ),
	fuel_06019( 6019, "Holz 45%w"        ,"Wood 45%w"        ,"Bois 45%eau"      ,"Madera 45%"       ,"Legno 45%w"       ,"Hout 45%w"        ,"Holz 45%w"        ,"Drevo 40%"        ,"Fa 45%w"          ,"Tr� 45%w"         ),
	fuel_06020( 6020, "Hackschnitzel"    ,"Woodchips"        ,"Copeau"           ,"Virutas"          ,"Trucioli legno"   ,"Houtsnippers"     ,"Tr�flis"          ,""                 ,"Faapr�t�k"        ,""                 ),
	fuel_07000( 7000, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_07001( 7001, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_07002( 7002, "Gaz GZ 50"        ,"Gaz GZ 50"        ,"Gaz GZ 50"        ,"Gaz GZ 50"        ,"Gaz GZ 50"        ,"Gas GZ 50"        ,"Gaz GZ 50"        ,"Gaz GZ 50"        ,"Gaz GZ 50"        ,"Gas GZ 50"        ),
	fuel_07003( 7003, "Gaz GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gas GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gaz GZ 41.5"      ,"Gas GZ 41.5"      ),
	fuel_07004( 7004, "Gaz GZ 35"        ,"Gaz GZ 35"        ,"Gaz GZ 35"        ,"Gaz GZ 35"        ,"Gaz GZ 35"        ,"Gas GZ 35"        ,"Gaz GZ 35"        ,"Gaz GZ 35"        ,"Gaz GZ 35"        ,"Gas GZ 35"        ),
	fuel_07005( 7005, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_07006( 7006, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_07007( 7007, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_07008( 7008, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_07009( 7009, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_07010( 7010, "W.brunatny"       ,"W.brunatny"       ,"W.brunatny"       ,"W.brunatny"       ,"Polvere di lignite","W.brunatny"       ,"W.brunatny"       ,"Hned� uhl� prach" ,"W.brunatny"       ,"W.brunatny"      ),
	fuel_07011( 7011, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_07012( 7012, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_07013( 7013, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_08000( 8000, "Heiz�l HEL"       ,"Fueloil HEL"      ,"Fioul dom HEL"    ,"Fueloil HEL"      ,"Fueloil HEL"      ,"Stookolie HEL"    ,"Heiz�l HEL"       ,"LTO"              ,"Futoolaj HEL"     ,"Fyringsolie HEL"  ),
	fuel_08001( 8001, "Heiz�l HL"        ,"Fueloil HL"       ,"Fioul dom HL"     ,"Fueloil HL"       ,"Fueloil HL"       ,"Stookolie HL"     ,"Heiz�l HL"        ,"LTO"              ,"Futoolaj HL"      ,"Fyringsolie HL"   ),
	fuel_08002( 8002, "Heiz�l HM"        ,"Fueloil HM"       ,"Fioul dom HM"     ,"Fueloil HM"       ,"Fueloil HM"       ,"Stookolie HM"     ,"Heiz�l HM"        ,"LTO"              ,"Futoolaj HM"      ,"Fyringsolie HM"   ),
	fuel_08003( 8003, "Heiz�l HS1"       ,"Fueloil HS1"      ,"Fioul dom HS1"    ,"Fueloil HS1"      ,"Fueloil HS1"      ,"Stookolie HS1"    ,"Heiz�l HS1"       ,"LTO"              ,"Futoolaj HS1"     ,"Fyringsolie HS1"  ),
	fuel_08004( 8004, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_08005( 8005, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_08006( 8006, "Erdgas"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Aardgas"          ,"Erdgas"           ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_08007( 8007, "Fl�ssiggas"       ,"LPG"              ,"Gaz liqu�fi�"     ,"Gas l�quido"      ,"Gas liquido"      ,"Vloeibaargas"     ,"Fl�ssiggas"       ,"Zkapalnen� plyn"  ,"LPG"              ,"Flydende gas"     ),
	fuel_08008( 8008, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_08009( 8009, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_08010( 8010, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_08011( 8011, "Steinkohle"       ,"Black coal"       ,"Houille"          ,"Hulla"            ,"Carbone fossile"  ,"Steenkool"        ,"Steinkohle"       ,"Cern� uhl�"       ,"Kosz�n"           ,"Sort kul"         ),
	fuel_08012( 8012, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_08013( 8013, "Holz 15%w"        ,"Wood 15%w"        ,"Bois 15%eau"      ,"Madera 15%"       ,"Legno 15%w"       ,"Hout 15%w"        ,"Holz 15%w"        ,"Drevo 15%"        ,"Fa 15%w"          ,"Tr� 15%w"         ),
	fuel_08014( 8014, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_08015( 8015, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_08016( 8016, "St�ckholz"        ,"Woodchips"        ,"Bois d�chiquet�"  ,"Virutas"          ,"Woodchips"        ,"Hout"             ,""                 ,"Kusov� drevo"     ,"Faforg�cs"        ,"Tr�piller"        ),
	fuel_08017( 8017, "Hackgut trocken"  ,"Wood Chips T"     ,"Plaquette s�che"  ,"Virutas T"        ,"Wood Chips T"     ,"Houtsnippers droog",""                 ,"�tepka such�"     ,"Faforg�cs T"      ,"Tr�piller T"     ),
	fuel_08018( 8018, "Hackgut feucht"   ,"Wood Chips M"     ,"Plaquette humide" ,"Virutas M"        ,"Wood Chips M"     ,"Houtsnippers vochtig",""                 ,"�tepka vlhk�"     ,"Faforg�cs M"      ,"Tr�piller M"   ),
	fuel_08020( 8020, "Steinkohle 10%"   ,"Coal 10%"         ,"Houille"          ,"Carb�n 10%"       ,"Coal 10%"         ,"Steenkool 10%"    ,""                 ,"Cern� uhl� 10%"   ,"Sz�n 10%"         ,"Kul 10%"          ),
	fuel_08021( 8021, "Gerste-Triticale" ,"Gerste-Triticale" ,"C�r�ale"          ,"Cebada-Triticale" ,"Gerste-Triticale" ,"Gerst, triticale" ,""                 ,"Jecmen"           ,"Gerste-Triticale" ,"Korn"             ),
	fuel_08024( 8024, "Biomasse"         ,"Biomass"          ,"Biomasse"         ,"Biomasa"          ,"Biomass"          ,"Biomass"          ,"Biomass"          ,"Biomasa"          ,"Biomassza"        ,"Biomass"          ),
	fuel_08028( 8028, "FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ,"FAME"             ),
	fuel_08029( 8029, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_08030( 8030, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_09000( 9000, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_09001( 9001, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_09002( 9002, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_09003( 9003, "Heiz�l # 2"       ,"Fuel oil # 2"     ,"Fioul # 2"        ,"Fueloil # 2"      ,"Fueloil # 2"      ,"Olie #2"          ,"Fueloil # 2"      ,"Topn� olej # 2"   ,"G�zolaj # 2"      ,"Br�ndselsolie # 2"),
	fuel_09004( 9004, "Heiz�l # 5"       ,"Fuel oil # 5"     ,"Fioul # 5"        ,"Fueloil # 5"      ,"Fueloil # 5"      ,"Olie #5"          ,"Fueloil # 5"      ,"Topn� olej # 5"   ,"G�zolaj # 5"      ,"Br�ndselsolie # 5"),
	fuel_09005( 9005, "Heiz�l # 6"       ,"Fuel oil # 6"     ,"Fioul # 6"        ,"Fueloil # 6"      ,"Fueloil # 6"      ,"Olie #6"          ,"Fueloil # 6"      ,"Topn� olej # 6"   ,"G�zolaj # 6"      ,"Br�ndselsolie # 6"),
	fuel_09006( 9006, "Petroleum"        ,"Kerosene"         ,"K�ros�ne"         ,"Queroseno"        ,"Kerosene"         ,"Kerosine"         ,"Kerosene"         ,"Kerosin"          ,"Kerozin"          ,"Kerosene"         ),
	fuel_09007( 9007, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_09008( 9008, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_09009( 9009, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_09010( 9010, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_09011( 9011, "Distillate # 1"   ,"Distillate # 1"   ,"Distillate # 1"   ,"Destilado # 1"    ,"Distillato # 1"   ,"Destilaat 1"      ,"Distillate # 1"   ,"Destil�t # 1"     ,"P�rlat # 1"       ,"Distillat # 1"    ),
	fuel_09012( 9012, "Holz 10%F."       ,"Wood 10%M."       ,"Bois 10%H"        ,"Madera 10%M."     ,"Legno 10%M."      ,"Hout 10%M"        ,"Wood 10%M."       ,"Drevo 10%M."      ,"Fa 10%M."         ,"Tr� 10%M."        ),
	fuel_09013( 9013, "Holz 20%F."       ,"Wood 20%M."       ,"Bois 20%H"        ,"Madera 20%M."     ,"Legno 20%M."      ,"Hout 20%M"        ,"Wood 20%M."       ,"Drevo 20%M."      ,"Fa 20%M."         ,"Tr� 20%M."        ),
	fuel_09014( 9014, "Holz 30%F."       ,"Wood 30%M."       ,"Bois 30%H"        ,"Madera 30%M."     ,"Legno 30%M."      ,"Wood 30%M."       ,"Wood 30%M."       ,"Drevo 30%M."      ,"Fa 30%M."         ,"Tr� 30%M."        ),
	fuel_09015( 9015, "Holz 40%F."       ,"Wood 40%M."       ,"Bois 40%H"        ,"Madera 40%M."     ,"Legno 40%M."      ,"Wood 40%M."       ,"Wood 40%M."       ,"Drevo 40%M."      ,"Fa 40%M."         ,"Tr� 40%M."        ),
	fuel_09016( 9016, "Rinde 15%F."      ,"Bark 15%M."       ,"Ecorce 15%H"      ,"Corteza 15%M."    ,"Corteccia 15%M."  ,"Schors 15%M"      ,"Bark 15%M."       ,"Kura 15%M."       ,"Fak�reg 15%M."    ,"Bark 15%M."       ),
	fuel_09017( 9017, "Rinde 30%F."      ,"Bark 30%M."       ,"Ecorce 30%H"      ,"Corteza 30%M."    ,"Bark 30%M."       ,"Bark 30%M."       ,"Bark 30%M."       ,"Kura 30%M."       ,"Fak�reg 30%M."    ,"Bark 30%M"        ),
	fuel_09018( 9018, "Rinde 45%F."      ,"Bark 45%M."       ,"Ecorce 45%H"      ,"Corteza 45%M."    ,"Corteccia 45%M."  ,"Schors 45%M"      ,"Bark 45%M."       ,"Kura 45%M."       ,"Fak�reg 45%M."    ,"Bark 45%M."       ),
	fuel_09019( 9019, "Rinde 60%F."      ,"Bark 60%M."       ,"Ecorce 60%H"      ,"Corteza 60%M."    ,"Corteccia 60%M."  ,"Schors 60%M"      ,"Bark 60%M."       ,"Kura 60%M."       ,"Fak�reg 60%M."    ,"Bark 60%M."       ),
	fuel_09020( 9020, "Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Bagazo"           ,"Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Vylisovan� cukrov� trtina","Pr�selt cukorn�d" ,"Bagasse"  ),
	fuel_09021( 9021, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_09022( 9022, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_09023( 9023, "Heiz�l"           ,"Gasoleo"          ,"Fioul dom"        ,"Gasoleo"          ,"Gasoleo"          ,"Gasolie"          ,"Gasoleo"          ,"Topn� olej"       ,"D�zel"            ,"Gasolie"          ),
	fuel_09024( 9024, "LPG"              ,"LPG"              ,"GPL"              ,"LPG"              ,"GPL"              ,"LPG"              ,"LPG"              ,"LPG"              ,"LPG"              ,"LPG"              ),
	fuel_09025( 9025, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_09026( 9026, "Bioheat 5"        ,"Bioheat 5"        ,"Bioheat 5"        ,"Biomasa 5"        ,"Bioheat 5"        ,"Biodiesel 5"      ,"Bioheat 5"        ,"Bionafta 5"       ,"Bioheat 5"        ,"Biofuel 5"        ),
	fuel_09027( 9027, ""                 ,"Bioheat 12"       ,"Bioheat 12"       ,"Bioheat 12"       ,"Bioheat 12"       ,""                 ,""                 ,"Bioheat 12"       ,""                 ,""                 ),
	fuel_09028( 9028, ""                 ,"Bioheat 20"       ,"Bioheat 20"       ,"Bioheat 20"       ,"Bioheat 20"       ,""                 ,""                 ,"Bioheat 20"       ,""                 ,""                 ),
	fuel_10000(10000, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_10001(10001, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_10002(10002, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_10003(10003, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_10004(10004, "Erdgas H"         ,"Natural gas"      ,"Gaz naturel H"    ,"Gas Natural"      ,"Erdgas H"         ,"Aardgas Hb"       ,"Erdgas H"         ,"Zemn� plyn "      ,"F�ldg�z"          ,"Naturgas"         ),
	fuel_10005(10005, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_10006(10006, "Gaskoks"          ,"Coke"             ,"Gaz de cokerie"   ,"Coque"            ,"Coke"             ,"Cokes"            ,"Coke"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_10007(10007, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_10008(10008, "Braunkohle rhein.","Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Lignite"          ,"Lignite"          ,"Lignit"           ,"Lignite"          ),
	fuel_10009(10009, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_10010(10010, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_10011(10011, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_10012(10012, "Bois "            ,"Bois "            ,"Bois "            ,"Madera"           ,"Bois "            ,"Hout E15"         ,"Bois "            ,"Drevo"            ,"Bois "            ,"Flis"             ),
	fuel_10013(10013, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_10014(10014, "G20"              ,"G20"              ,"G20"              ,"G20"              ,"G20"              ,"G20"              ,""                 ,"G20"              ,"G20"              ,"G20"              ),
	fuel_10015(10015, "G25"              ,"G25"              ,"G25"              ,"G25"              ,"G25"              ,"G25"              ,""                 ,"G25"              ,"G25"              ,"G25"              ),
	fuel_10016(10016, "G30"              ,"G30"              ,"G30"              ,"G30"              ,"G30"              ,"G30"              ,""                 ,"G30"              ,"G30"              ,"G30"              ),
	fuel_10017(10017, "Holz 30%F."       ,"Wood 30%M."       ,"Bois 30%H"        ,"Madera 30%M."     ,"Legno 30%M."      ,"Wood 30%M."       ,"Wood 30%M."       ,"Drevo 30%M."      ,"Fa 30%M."         ,"Tr� 30%M."        ),
	fuel_10018(10018, "Rinde 45%F."      ,"Bark 45%M."       ,"Ecorce 45%H"      ,"Corteza 45%M."    ,"Corteccia 45%M."  ,"Schors 45%M"      ,"Bark 45%M."       ,"Kura 45%M."       ,"Fak�reg 45%M."    ,"Bark 45%M."       ),
	fuel_10019(10019, "Rinde 60%F."      ,"Bark 60%M."       ,"Ecorce 60%H"      ,"Corteza 60%M."    ,"Corteccia 60%M."  ,"Schors 60%M"      ,"Bark 60%M."       ,"Kura 60%M."       ,"Fak�reg 60%M."    ,"Bark 60%M."       ),
	fuel_11000(11000, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_11001(11001, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_11002(11002, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_11003(11003, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_11004(11004, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_11005(11005, "Erdgas H"         ,"Natural gas"      ,"Gaz naturel H"    ,"Gas Natural"      ,"Erdgas H"         ,"Aardgas Hb"       ,"Erdgas H"         ,"Zemn� plyn "      ,"F�ldg�z"          ,"Naturgas"         ),
	fuel_11006(11006, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_11007(11007, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_11008(11008, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_11009(11009, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_11010(11010, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_12000(12000, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_12001(12001, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_12002(12002, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_12003(12003, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_12004(12004, "Erdgas H"         ,"Natural gas"      ,"Gaz naturel H"    ,"Gas Natural"      ,"Erdgas H"         ,"Aardgas Hb"       ,"Erdgas H"         ,"Zemn� plyn "      ,"F�ldg�z"          ,"Naturgas"         ),
	fuel_12005(12005, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_12006(12006, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_12007(12007, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_12008(12008, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_12009(12009, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_12010(12010, "Kokereigas"       ,"Coke oven gas"    ,"Gaz de cokerie"   ,"Gas de coque"     ,"Gas Coker"        ,"Cokesgas"         ,"Kokereigas"       ,"Koks�rensk� plyn" ,"Kokszg�z"         ,"Koks-gas"         ),
	fuel_12011(12011, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_12012(12012, "Holz 15%w"        ,"Wood 15%w"        ,"Bois 15%eau"      ,"Madera 15%"       ,"Legno 15%w"       ,"Hout 15%w"        ,"Holz 15%w"        ,"Drevo 15%"        ,"Fa 15%w"          ,"Tr� 15%w"         ),
	fuel_12013(12013, "Holzpellets"      ,"Wood Pellets"     ,"Pellets"          ,"Pellets"          ,"Legno/Pellets"    ,"Pellets"          ,"Holzpellets"      ,"Dreven� pelety"   ,"Fa pellet"        ,"Tr�briketter"     ),
	fuel_13000(13000, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_13001(13001, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_13002(13002, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_13003(13003, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_13004(13004, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_13005(13005, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_13006(13006, "Braunkohle rhein.","Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Lignite"          ,"Lignite"          ,"Lignit"           ,"Lignite"          ),
	fuel_13007(13007, "Husk"             ,"Husk"             ,"Husk"             ,"C�scara"          ,"Husk"             ,"Schillen"         ,"Husk"             ,"Husk"             ,"N�v�nyburok"      ,"Kvas"             ),
	fuel_13008(13008, "Holz"             ,"Wood"             ,"Bois"             ,"Madera"           ,"Legno"            ,"Hout"             ,"Wood"             ,"Drevo"            ,"Fa"               ,"Tr�"              ),
	fuel_13009(13009, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_13010(13010, "Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Bagazo"           ,"Bagasse"          ,"Bagasse"          ,"Bagasse"          ,"Vylisovan� cukrov� trtina","Pr�selt cukorn�d" ,"Bagasse"  ),
	fuel_14000(14000, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_14001(14001, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_14002(14002, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_14003(14003, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_14004(14004, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_14005(14005, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_14006(14006, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_14007(14007, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            ),
	fuel_14008(14008, "Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Diesel"           ,"Nafta"            ,"D�zel"            ,"Diesel"           ),
	fuel_14009(14009, "Benzin"           ,"Gasoline"         ,"Essence"          ,"Gasolina"         ,"Benzina"          ,"Benzine"          ,"Benzin"           ,"Benzin"           ,"Benzin"           ,"Letolie"          ),
	fuel_14010(14010, "Husk"             ,"Husk"             ,"Husk"             ,"C�scara"          ,"Husk"             ,"Schillen"         ,"Husk"             ,"Husk"             ,"N�v�nyburok"      ,"Kvas"             ),
	fuel_14011(14011, "Holz"             ,"Wood"             ,"Bois"             ,"Madera"           ,"Legno"            ,"Hout"             ,"Wood"             ,"Drevo"            ,"Fa"               ,"Tr�"              ),
	fuel_14012(14012, "cane"             ,"Cane"             ,"Canne"            ,"Mimbre"           ,"Cane"             ,"Riet"             ,"cane"             ,"cane"             ,"N�d"              ,"cane"             ),
	fuel_15000(15000, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_15001(15001, "Fl�ssiggas"       ,"LPG"              ,"Gaz liqu�fi�"     ,"Gas l�quido"      ,"Gas liquido"      ,"Vloeibaargas"     ,"Fl�ssiggas"       ,"Propan"           ,"LPG"              ,"Flydende gas"     ),
	fuel_15002(15002, "Stadtgas"         ,"Town gas"         ,"Gaz de ville"     ,"Gas Ciudad"       ,"Gas citt�"        ,"Gas"              ,"Stadtgas"         ,"Mestsk� plyn"     ,"V�rosig�z"        ,"Bygas"            ),
	fuel_15003(15003, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_15004(15004, "Heiz�l-EL 5"      ,"Petroleo 5"       ,"Fioul dom 5"      ,"Petroleo 5"       ,"Petroleo 5"       ,"Petroleum 5"      ,"Petroleo 5"       ,"Petroleo 5"       ,"G�zolaj 5"        ,"Petroleum 5"      ),
	fuel_15005(15005, "Heiz�l-EL 6"      ,"Petroleo 6"       ,"Fioul dom 6"      ,"Petroleo 6"       ,"Petroleo 6"       ,"Petroleum 6"      ,"Petroleo 6"       ,"Petroleo 6"       ,"G�zolaj 6"        ,"Petroleum 6"      ),
	fuel_15006(15006, "Petroleum"        ,"Kerosene"         ,"K�ros�ne"         ,"Queroseno"        ,"Kerosene"         ,"Kerosine"         ,"Kerosene"         ,"Kerosin"          ,"Kerozin"          ,"Kerosene"         ),
	fuel_15007(15007, "Laubholz"         ,"Lena"             ,"Lena"             ,"Le�a"             ,"Lena"             ,"Hardhout"         ,"Lena"             ,"Tvrd� drevo"      ,"Lena"             ,"Tr�kvas"          ),
	fuel_15008(15008, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_15009(15009, "Brikett"          ,"Briquette"        ,"Briquette"        ,"Briquita"         ,"Mattonella di lignite","Brikett"          ,"Brikett"          ,"Brikety"          ,"Brikett"          ,"Briket"       ),
	fuel_15010(15010, "Fettkohle"        ,"Coal"             ,"Charbon"          ,"Carb�n"           ,"Carbone"          ,"Kolen"            ,"Coal"             ,"Uhl�"             ,"Sz�n"             ,"Kul"              ),
	fuel_16000(16000, "Heiz�l EL"        ,"Light Oil"        ,"Fioul dom"        ,"Combustible ligero","Gasolio"          ,"Stookolie EL"     ,"Heiz�l EL"        ,"LTO"              ,"Futoolaj k�nnyu"  ,"Letolie"         ),
	fuel_16001(16001, "Heiz�l S"         ,"Heavy Oil"        ,"Fioul lourd"      ,"Combustible pesado","Olio combustibile","Stookolie S"      ,"Heiz�l S"         ,"TTO"              ,"Futoolaj neh�z"   ,"Sv�rolie"        ),
	fuel_16002(16002, "Methan"           ,"Natural gas"      ,"Gaz naturel"      ,"Gas Nat."         ,"Metano"           ,"Biogas"           ,"Natural Gas"      ,"Zemn� plyn"       ,"F�ldg�z"          ,"N-gas"            ),
	fuel_16003(16003, "Heiz�l"           ,"Gasoleo"          ,"Fioul dom"        ,"Gasoleo"          ,"Gasoleo"          ,"Gasolie"          ,"Gasoleo"          ,"Topn� olej"       ,"D�zel"            ,"Gasolie"          ),
	fuel_16004(16004, "Propan"           ,"Propane"          ,"Propane"          ,"Propano"          ,"Propano"          ,"Propaan"          ,"Propane"          ,"Propan"           ,"Prop�n"           ,"Flaskegas"        ),
	fuel_16005(16005, "Koks"             ,"Coke"             ,"Coke"             ,"Coque"            ,"Coke"             ,"Cokes"            ,"Koks"             ,"Koks"             ,"Koksz"            ,"Koks"             ),
	fuel_16006(16006, "Braunkohle"       ,"Lignite"          ,"Lignite"          ,"Lignito"          ,"Lignite"          ,"Bruinkool"        ,"Braunkohle"       ,"Hned� uhl�"       ,"Barnasz�n"        ,"Brunkul"          ),
	fuel_16007(16007, "Anthrazit"        ,"Anthracite"       ,"Anthracite"       ,"Antracita"        ,"Antracite"        ,"Antraciet"        ,"Anthracit"        ,"Antracit"         ,"Anthracit"        ,"Antracit"         ),
	fuel_16008(16008, "Butan"            ,"Butane"           ,"Butane"           ,"Butano"           ,"Butano"           ,"Butaan"           ,"Butan"            ,"Butan"            ,"But�n"            ,"Butan"            );
                       
	
	/**
	 * fuel number
	 */
	private final int number;

	/**
	 * fuel text in DE
	 */
	private final String text_de;

	/**
	 * fuel text in EN
	 */
	private final String text_en;

	/**
	 * fuel text in FR
	 */
	private final String text_fr;

	/**
	 * fuel text in ES
	 */
	private final String text_es;

	/**
	 * fuel text in IT
	 */
	private final String text_it;

	/**
	 * fuel text in NL
	 */
	private final String text_nl;

	/**
	 * fuel text in SV
	 */
	private final String text_sv;

	/**
	 * fuel text in CS
	 */
	private final String text_cs;

	/**
	 * fuel text in HU
	 */
	private final String text_hu;

	/**
	 * fuel text in DA
	 */
	private final String text_da;

	
	
	/**
	 * Constructor
	 * @param number fuel number
	 * @param text_de fuel text in DE
	 * @param text_en fuel text in EN
	 * @param text_fr fuel text in FR
	 * @param text_es fuel text in ES
	 * @param text_it fuel text in IT
	 * @param text_nl fuel text in NL
	 * @param text_sv fuel text in SV
	 * @param text_cs fuel text in CS
	 * @param text_hu fuel text in HU
	 * @param text_da fuel text in DA
	 */
	private Fuels(int number, String text_de, String text_en, String text_fr,
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
	 * get fuel number
	 * @return fuel number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * get fuel text 
	 * @return fuel text
	 */
	public String getText(){
		return getText(getNumber());
	}

	/**
	 * get fuel text by number
	 * @param number fuel id
	 * @return fuel text
	 */
	public static String getText(int number){
		try{
			String languageCode = Locale.getDefault().getLanguage();
			Log.d("Fuels", "getText. Language Code = " + languageCode);
			Fuels item = Fuels.getItemByCode(number);
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
			Log.e("Fuels", "getText. Cannot get text for fuel id = " + number + ". Error: " + ex);
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
	 * @param number fuel number
	 * @return enum item or null if not found 
	 */
	public static Fuels getItemByCode(int number){
		for (Fuels i : Fuels.values()){
			if (i.getNumber() == number)
				return i;
		}
		return null;
	}
	

}; // enum

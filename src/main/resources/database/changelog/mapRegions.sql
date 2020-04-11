--liquibase formatted sql
--changeset andrew:2020-04-10
CREATE TABLE IF NOT EXISTS "mapRegions" (
	"regionID"	INTEGER NOT NULL,
	"regionName"	VARCHAR(100),
	"x"	FLOAT,
	"y"	FLOAT,
	"z"	FLOAT,
	"xMin"	FLOAT,
	"xMax"	FLOAT,
	"yMin"	FLOAT,
	"yMax"	FLOAT,
	"zMin"	FLOAT,
	"zMax"	FLOAT,
	"factionID"	INTEGER,
	"radius"	FLOAT,
	PRIMARY KEY("regionID")
);
INSERT INTO "mapRegions" VALUES (10000001,'Derelik',-7.73619519227769e+16,5.08780326643019e+16,-6.44331012661154e+16,-1.05549987563848e+17,-4.91739162817057e+16,2.7128553877044e+16,7.46275114515598e+16,2.64233605110287e+16,1.02442842021202e+17,500007,NULL);
INSERT INTO "mapRegions" VALUES (10000002,'The Forge',-9.64203296646176e+16,6.40270758377404e+16,1.12539817132904e+17,-1.43645654698282e+17,-4.9195004630953e+16,3.51545563967551e+16,9.28995952787257e+16,-1.44452603161759e+17,-8.06270311040498e+16,500001,NULL);
INSERT INTO "mapRegions" VALUES (10000003,'Vale of the Silent',-4.40693233378379e+16,9.47294441524583e+16,1.81384695885577e+17,-9.92337602607694e+16,1.10951135850936e+16,5.82041704075143e+16,1.31254717897402e+17,-2.18879593357516e+17,-1.43889798413638e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000004,'UUA-F4',8.98680042578759e+16,5.47800960067286e+16,2.72575750367217e+17,6.73908273200137e+16,1.12345181195738e+17,1.38650391165171e+16,9.569515289694e+16,-3.8077417785062e+17,-1.64377322883813e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000005,'Detorid',1.33540404993619e+17,-3.13915018760047e+16,-1.96392258478522e+17,5.80859178076883e+16,2.0899489217955e+17,-5.07203320489026e+16,-1.20626717031067e+16,1.64748864800978e+17,2.28035652156066e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000006,'Wicked Creek',9.62325935132744e+16,2.54172264031303e+15,-1.62683939101785e+17,5.50942891882897e+16,1.37370897838259e+17,-1.67797177739814e+16,2.18631630546075e+16,1.42341340477442e+17,1.83026537726129e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000007,'Cache',2.44727810799539e+17,-1.61851294435097e+16,-8.50732831493953e+16,1.83951658928238e+17,3.05503962670839e+17,-4.21314900311268e+16,9.76123114410739e+15,4.99181459418285e+16,1.20228420356962e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000008,'Scalding Pass',6.41368176370205e+16,5.11467958470643e+15,-1.38210100477706e+17,1.86366608740231e+16,1.09636974400018e+17,-2.14856603573735e+16,3.17150195267864e+16,1.03993036168969e+17,1.72427164786443e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000009,'Insmother',1.54196834892712e+17,2.83511117926583e+15,-1.45389178945985e+17,1.08381641944456e+17,2.00012027840969e+17,-4.12171001792075e+16,4.68873225377392e+16,9.83663313091254e+16,1.92412026582845e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000010,'Tribute',-1.20113706247411e+17,1.00251692022651e+17,2.24257843400491e+17,-1.80136061822225e+17,-6.00913506725963e+16,8.17445109258689e+16,1.18758873119434e+17,-2.48701329588798e+17,-1.99814357212184e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000011,'Great Wildlands',5.25138376302241e+16,2.90566275938602e+16,-7.27592077175993e+16,1.57875781665387e+16,8.92400970939095e+16,-4.41701945232586e+15,6.25302746400463e+16,3.77244332844532e+16,1.07793982150745e+17,500015,NULL);
INSERT INTO "mapRegions" VALUES (10000012,'Curse',1.3928759309586e+16,-1.279955725086e+15,-1.51114759582865e+17,-1.71649024744228e+16,4.50224210935949e+16,-3.02658596278698e+16,2.77059481776978e+16,1.35105384527697e+17,1.67124134638034e+17,500011,NULL);
INSERT INTO "mapRegions" VALUES (10000013,'Malpais',1.75707909414576e+17,6.84868679773345e+16,9.14413531042616e+16,1.46522777958075e+17,2.04893040871076e+17,4.3221875094774e+16,9.37518608598951e+16,-1.36571342987508e+17,-4.63113632210148e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000014,'Catch',-1.13595452153499e+17,2.1828796395059e+16,-2.00997188121673e+17,-1.76804941309964e+17,-5.03859629970339e+16,-1.06431528139274e+16,5.43007456040453e+16,1.39121469927841e+17,2.62872906315506e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000015,'Venal',-1.23149072132624e+17,1.11405178366885e+17,3.08325578657564e+17,-1.74025244531813e+17,-7.22728997334341e+16,9.24544399875372e+16,1.30355916746234e+17,-3.6182206030276e+17,-2.54829097012368e+17,500010,NULL);
INSERT INTO "mapRegions" VALUES (10000016,'Lonetrek',-1.89171222177239e+17,9.45524633509496e+16,1.5569612898227e+17,-2.33466109933311e+17,-1.44876334421167e+17,6.99908433405749e+16,1.19114083361324e+17,-1.93878176320107e+17,-1.17514081644434e+17,500001,NULL);
INSERT INTO "mapRegions" VALUES (10000017,'J7HZ-F',9.26204495038799e+15,8.14430314284755e+16,2.31053798464132e+17,-3.09904199591223e+16,4.95145098598983e+16,5.98749125846423e+16,1.03011150272309e+17,-2.78250620384738e+17,-1.83856976543526e+17,500005,NULL);
INSERT INTO "mapRegions" VALUES (10000018,'The Spire',2.32956156642256e+17,3.1778178062885e+16,2.33673656185098e+16,2.02456187021459e+17,2.63456126263052e+17,-2.64509410030193e+15,6.62014502260719e+16,-7.00181972826575e+16,2.32834660456378e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000019,'A821-A',1.36806722191089e+16,7.06814661355099e+16,2.84135649247806e+17,-6.9809772349774e+15,3.43423216731951e+16,5.66671873025769e+16,8.46957449684428e+16,-3.33999270054647e+17,-2.34272028440965e+17,500005,NULL);
INSERT INTO "mapRegions" VALUES (10000020,'Tash-Murkon',-2.11753371863879e+17,6.2838705971068e+16,-1.23934814537664e+17,-2.39002211770169e+17,-1.8450453195759e+17,3.36472743599677e+16,9.20301375821683e+16,8.85444905536914e+16,1.59325138521638e+17,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000021,'Outer Passage',2.91498150006239e+17,4.08628362683461e+15,8.03076834578899e+16,2.53707828044976e+17,3.29288471967503e+17,-5.7431940876101e+16,6.56045081297702e+16,-1.47627280712887e+17,-1.29880862028926e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000022,'Stain',-1.58827678964844e+17,5.86911904756425e+16,-3.09223961465603e+17,-2.20896070661677e+17,-9.67592872680104e+16,1.64015339885485e+16,1.00980846962736e+17,2.54043792338772e+17,3.64404130592435e+17,500019,NULL);
INSERT INTO "mapRegions" VALUES (10000023,'Pure Blind',-2.60921882715692e+17,7.89792661914274e+16,2.20225532514732e+17,-3.19394067132735e+17,-2.02449698298649e+17,3.65273054064862e+16,1.21431226976369e+17,-2.38553930773527e+17,-2.01897134255937e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000025,'Immensea',2.18929141598659e+16,-3.0178289457794e+15,-2.05784180881658e+17,-2.28953868973768e+16,6.66812152171086e+16,-2.35303393969113e+16,1.74946815053525e+16,1.65767307374258e+17,2.45801054389059e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000027,'Etherium Reach',1.28382041362752e+17,5.63771470967499e+16,3.78681983031999e+16,6.79555705924636e+16,1.8880851213304e+17,2.90848064204492e+16,8.36694877730506e+16,-7.99584057326113e+16,4.22200912621145e+15,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000028,'Molden Heath',-3.57221136222882e+16,4.35032539761976e+15,-5.3761035732505e+15,-5.71246322750231e+16,-1.43195949695533e+16,-1.10255693317814e+16,1.97262201270209e+16,-7.29743765632795e+15,1.8049644802829e+16,500002,NULL);
INSERT INTO "mapRegions" VALUES (10000029,'Geminate',-1.58339265318656e+16,5.9778184289369e+16,1.15546529090021e+17,-4.51374800544612e+16,1.34696269907301e+16,2.35419857795319e+16,9.6014382799206e+16,-1.37261979990137e+17,-9.38310781899044e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000030,'Heimatar',-9.29292854916007e+16,3.21243916270895e+16,2.41487213993692e+16,-1.28269338426337e+17,-5.75892325568641e+16,5.11770923668872e+15,5.91310740174903e+16,-5.15767742261278e+16,3.27933142738942e+15,500002,NULL);
INSERT INTO "mapRegions" VALUES (10000031,'Impass',-3.86061671297053e+16,-1.84003793729434e+15,-3.34948558437469e+17,-4.62721531634544e+16,-3.09401810959562e+16,-3.05280484810207e+16,2.68479726064321e+16,3.05300973053793e+17,3.64596143821145e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000032,'Sinq Laison',-1.8191288307648e+17,4.16995387869047e+16,3.97807386770742e+16,-2.1861963981745e+17,-1.4520612633551e+17,1.48380410116087e+16,6.85610365622007e+16,-8.52575992950059e+16,5.69612194085744e+15,500004,NULL);
INSERT INTO "mapRegions" VALUES (10000033,'The Citadel',-1.54620630524369e+17,7.85949741104122e+16,1.04821797629467e+17,-1.87991094902498e+17,-1.21250166146241e+17,4.73322945841131e+16,1.09857653636711e+17,-1.48876595282702e+17,-6.07669999762324e+16,500001,NULL);
INSERT INTO "mapRegions" VALUES (10000034,'The Kalevala Expanse',1.33941223410126e+17,5.48098880590211e+16,9.49795793781928e+16,1.10709276315877e+17,1.57173170504376e+17,2.82901795268722e+16,8.132959659117e+16,-1.3003897205883e+17,-5.99201866975554e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000035,'Deklein',-2.76352540202201e+17,8.97693275886591e+16,3.10283495605706e+17,-3.57288458792002e+17,-1.954166216124e+17,5.18059655119402e+16,1.27732689665378e+17,-3.52811725566155e+17,-2.67755265645256e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000036,'Devoid',-1.41697960063521e+17,5.84415722744845e+16,-6.33341101553482e+16,-1.69282385386756e+17,-1.14113534740286e+17,2.20565223655273e+16,9.48266221834416e+16,3.03229716804472e+16,9.63452486302491e+16,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000037,'Everyshore',-1.94822013294281e+17,8.82766302928309e+15,2.02559538049208e+16,-2.15583579631562e+17,-1.74060446957e+17,-1.25330145532052e+16,3.01883406117714e+16,-3.12152391248727e+16,-9.29666848496889e+15,500004,NULL);
INSERT INTO "mapRegions" VALUES (10000038,'The Bleak Lands',-1.59172230516598e+17,3.1388510588133e+16,-5.09367503653776e+16,-1.7483440740064e+17,-1.43510053632557e+17,1.70381991240675e+15,6.10732012638592e+16,3.28615815053855e+16,6.90119192253696e+16,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000039,'Esoteria',-8.67878647136856e+16,-1.69771319821958e+16,-4.17232597484758e+17,-1.21897659027506e+17,-5.16780703998651e+16,-6.01297519478345e+16,2.61754879834429e+16,3.62533200787439e+17,4.71931994182076e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000040,'Oasa',2.33391636757061e+17,6.33356815054524e+16,1.22542259755245e+17,1.94254750307969e+17,2.72528523206153e+17,3.54992761639762e+16,9.11720868469286e+16,-1.74387649267655e+17,-7.06968702428352e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000041,'Syndicate',-3.21246237025975e+17,1.94581306252099e+16,7.41640134588306e+16,-3.62419022806808e+17,-2.80073451245142e+17,-7.41201724670466e+15,4.63282784971244e+16,-1.11626842054837e+17,-3.67011848628242e+16,500009,NULL);
INSERT INTO "mapRegions" VALUES (10000042,'Metropolis',-7.4267055557312e+16,3.57986387205552e+16,4.5153300095399e+16,-1.37997971841353e+17,-1.05361392732709e+16,-6.95309320897108e+15,7.85503706500816e+16,-7.9085709291585e+16,-1.1220890899213e+16,500002,NULL);
INSERT INTO "mapRegions" VALUES (10000043,'Domain',-2.00437833726153e+17,5.35617009322068e+16,-8.13552390856334e+16,-2.5404992057699e+17,-1.46825746875317e+17,1.29994217837693e+16,9.41239800806443e+16,1.82037728918344e+16,1.44506705279432e+17,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000044,'Solitude',-3.21931746318951e+17,2.82563292166004e+16,1.92761063328414e+16,-3.39484138934816e+17,-3.04379353703085e+17,4.65055463885324e+15,5.18621037943476e+16,-4.41186420514449e+16,5.56642938576211e+15,500004,NULL);
INSERT INTO "mapRegions" VALUES (10000045,'Tenal',-7.22878851336202e+16,5.83402765876594e+16,4.33602446107849e+17,-1.16748577875113e+17,-2.78271923921271e+16,1.51079457624212e+16,1.01572607412898e+17,-4.58300452818907e+17,-4.08904439396791e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000046,'Fade',-2.93951880420984e+17,5.87388643638547e+16,2.57748117411352e+17,-3.21962586144748e+17,-2.6594117469722e+17,3.46631658809204e+16,8.28145628467889e+16,-2.62210335614023e+17,-2.53285899208681e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000047,'Providence',-1.21750759712201e+17,5.84956514023328e+16,-1.51515874111716e+17,-1.47682648205223e+17,-9.58188712191787e+16,3.11091127245759e+16,8.58821900800897e+16,1.04761552559676e+17,1.98270195663755e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000048,'Placid',-2.70798690016689e+17,7.37017968120536e+16,9.54644596776992e+16,-2.95050806700007e+17,-2.46546573333371e+17,3.36872926317309e+16,1.13716300992376e+17,-1.35456400892811e+17,-5.54725184625871e+16,500004,NULL);
INSERT INTO "mapRegions" VALUES (10000049,'Khanid',-3.18287416184579e+17,2.04194140942048e+16,-1.33487524109219e+17,-3.64404836850034e+17,-2.72169995519124e+17,-2.33759555544046e+16,6.42147837428142e+16,9.43131932414724e+16,1.72661854976965e+17,500008,NULL);
INSERT INTO "mapRegions" VALUES (10000050,'Querious',-3.63413099563972e+17,5.32206504463178e+16,-2.40706130531269e+17,-4.21044001198138e+17,-3.05782197929807e+17,1.70995639818707e+16,8.93417369107648e+16,1.78498284629578e+17,3.0291397643296e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000051,'Cloud Ring',-3.20933851367644e+17,7.44221973684437e+16,1.46459468769175e+17,-3.34343216505568e+17,-3.07524486229721e+17,5.45042517911353e+16,9.43401429457521e+16,-1.76405700933117e+17,-1.16513236605233e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000052,'Kador',-2.37297480079981e+17,4.06803388298497e+16,-7.68309672978193e+16,-2.65099009094967e+17,-2.09495951064995e+17,1.42393603640819e+16,6.71213172956175e+16,2.19472682490724e+16,1.31714666346566e+17,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000053,'Cobalt Edge',2.92184365554962e+17,5.3493388123815e+16,2.04625108850392e+17,2.73407634801881e+17,3.10961096308044e+17,3.33471404002244e+16,7.36396358474057e+16,-2.6495783155322e+17,-1.44292386147564e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000054,'Aridia',-3.68177411221382e+17,1.8612570807884e+16,-7.77703156867333e+16,-4.13892030804226e+17,-3.22462791638538e+17,-5.88318298373161e+15,4.31083245994996e+16,3.85104046706074e+16,1.17030226702859e+17,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000055,'Branch',-1.45831957034714e+17,6.69152473299556e+16,4.12717925093977e+17,-1.92334882877541e+17,-9.93290311918867e+16,4.58998266984162e+16,8.79306679614949e+16,-4.68006802398913e+17,-3.57429047789041e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000056,'Feythabolis',3.06473180472628e+16,-1.60973888340617e+16,-4.03080457499606e+17,-1.34273592513705e+16,7.47219953458962e+16,-4.75388413079439e+16,1.53440636398205e+16,3.38718234219339e+17,4.67442680779874e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000057,'Outer Ring',-3.92563738530011e+17,2.16719327072278e+16,8.63772810266471e+16,-4.40630233235487e+17,-3.44497243824534e+17,8.45468549621118e+15,3.48891799182445e+16,-1.14159691483192e+17,-5.85948705701025e+16,500014,NULL);
INSERT INTO "mapRegions" VALUES (10000058,'Fountain',-4.49013589606488e+17,3.72273790653831e+16,-6.02436903624757e+15,-5.03218614776421e+17,-3.94808564436556e+17,1.2825904470868e+16,6.16288536598981e+16,-5.2236755323923e+16,6.42854933964182e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000059,'Paragon Soul',-1.13652861165762e+17,-2.63610116407273e+16,-4.71184672814804e+17,-1.47103312663208e+17,-8.02024096683158e+16,-4.78847916401597e+16,-4.83723164129496e+15,4.62265945308572e+17,4.80103400321037e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000060,'Delve',-4.29369275408752e+17,5.14261210036259e+16,-2.56560110972769e+17,-4.65524711410823e+17,-3.93213839406681e+17,2.07843019851014e+16,8.20679400221505e+16,1.87448411080601e+17,3.25671810864938e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000061,'Tenerifis',4.73954351430703e+16,-1.20252783045126e+16,-2.85986932642694e+17,-3.11422520279319e+16,1.25933122314073e+17,-4.1257255525995e+16,1.72066989169697e+16,2.42460862907173e+17,3.29513002378215e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000062,'Omist',1.00319291593819e+17,-2.74861101664417e+16,-3.89560313235004e+17,5.24683243798056e+16,1.48170258807832e+17,-4.64264150594276e+16,-8.54580527345574e+15,3.68934289108777e+17,4.10186337361231e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000063,'Period Basis',-4.14504229299553e+17,8.37884856539599e+16,-3.86878506419457e+17,-4.40202713206804e+17,-3.88805745392302e+17,6.61060451659501e+16,1.0147092614197e+17,3.55667366886337e+17,4.18089645952577e+17,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000064,'Essence',-2.18476380293209e+17,3.60796913432749e+16,5.374393363481e+16,-2.37409458368698e+17,-1.99543302217719e+17,7.63359742440284e+15,6.4525785262147e+16,-7.96099900064932e+16,-2.78778772631268e+16,500004,NULL);
INSERT INTO "mapRegions" VALUES (10000065,'Kor-Azor',-2.96212317520309e+17,4.16186484702717e+16,-1.04651247949499e+17,-3.58902782416633e+17,-2.33521852623984e+17,-1.01508065143189e+15,8.42523775919754e+16,7.28202220358183e+16,1.3648227386318e+17,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000066,'Perrigen Falls',2.11874376981372e+17,2.59047481640165e+16,1.0710786683887e+17,1.76484345431306e+17,2.47264408531438e+17,-5.43419127781609e+15,5.7243687605849e+16,-1.65684697545403e+17,-4.85310361323373e+16,NULL,NULL);
INSERT INTO "mapRegions" VALUES (10000067,'Genesis',-2.60029907705174e+17,1.48896484523138e+16,-1.54319941304413e+16,-2.99197792151819e+17,-2.20862023258529e+17,-1.97626992750815e+16,4.95419961797091e+16,-2.32802124881732e+16,5.41442007490558e+16,500003,NULL);
INSERT INTO "mapRegions" VALUES (10000068,'Verge Vendor',-2.4249831284325e+17,4.11701036114539e+16,5.15574510559666e+16,-2.58065601351461e+17,-2.26931024335039e+17,3.15825336093158e+16,5.0757673613592e+16,-6.5212930160258e+16,-3.79019719516752e+16,500004,NULL);
INSERT INTO "mapRegions" VALUES (10000069,'Black Rise',-2.22687068034734e+17,7.75594106422084e+16,1.36029596082309e+17,-3.03606258828316e+17,-1.41767877241152e+17,-3.35978015137349e+15,1.5847860143579e+17,-2.1694878687589e+17,-5.51104052887266e+16,500001,NULL);
INSERT INTO "mapRegions" VALUES (11000001,'A-R00001',7.6376170763493e+18,1.53938548528604e+18,-9.49761120633649e+18,7.62372970580783e+18,7.66515729534589e+18,1.51867169051701e+18,1.56009928005507e+18,9.47793351789179e+18,9.51936110742985e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000002,'A-R00002',7.60093452720375e+18,1.53937228994168e+18,-9.43050266383318e+18,7.54791022224037e+18,7.6606644918872e+18,1.48299515511827e+18,1.59574942476509e+18,9.37369281178424e+18,9.48644708143107e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000003,'A-R00003',7.66138628000036e+18,1.53936778897025e+18,-9.33859380181364e+18,7.60993163074189e+18,7.71285837914485e+18,1.48790441476877e+18,1.59083116317173e+18,9.28512427019715e+18,9.38805101860011e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000004,'B-R00004',7.76033681841794e+18,1.53934949521826e+18,-9.34005958951567e+18,7.73712696111448e+18,7.7932804103653e+18,1.51127277059285e+18,1.56742621984368e+18,9.31008265934467e+18,9.3662361085955e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000005,'B-R00005',7.87407952364896e+18,1.53937548374647e+18,-9.50307128858908e+18,7.83828528059015e+18,7.92769229280426e+18,1.49467197763941e+18,1.58407898985352e+18,9.45711810540402e+18,9.54652511761814e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000006,'B-R00006',7.83104454414719e+18,1.53938267375931e+18,-9.65985350373619e+18,7.79994882705669e+18,7.86546701886105e+18,1.50662357785713e+18,1.57214176966149e+18,9.62724856472384e+18,9.6927667565282e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000007,'B-R00007',7.634045819572e+18,1.53936591888914e+18,-9.73238855564729e+18,7.58213191849959e+18,7.67956658609747e+18,1.4906485850902e+18,1.58808325268809e+18,9.67403431968661e+18,9.7714689872845e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000008,'B-R00008',7.50509863182053e+18,1.53934455171856e+18,-9.63712977069197e+18,7.46253099555506e+18,7.54254331247867e+18,1.49933839325675e+18,1.57935071018036e+18,9.59611621761083e+18,9.67612853453444e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000009,'C-R00009',7.75699845847083e+18,1.53936284785932e+18,-9.83833510697693e+18,7.73389261027392e+18,7.79243065915667e+18,1.51009382341794e+18,1.56863187230069e+18,9.80832498880012e+18,9.86686303768287e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000010,'C-R00010',7.4580208974422e+18,1.53938684407856e+18,-9.70053875668015e+18,7.42573545603561e+18,7.4906177570058e+18,1.50694569359346e+18,1.57182799456366e+18,9.66471986025467e+18,9.72960216122487e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000011,'C-R00011',7.40480108808294e+18,1.53941550326979e+18,-9.45356649904654e+18,7.36124648528109e+18,7.44416028674046e+18,1.4979586025401e+18,1.58087240399947e+18,9.41240985656068e+18,9.49532365802006e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000012,'C-R00012',7.46732745097755e+18,1.53941094882098e+18,-9.32407984332594e+18,7.42513594855905e+18,7.50780366644981e+18,1.4980770898756e+18,1.58074480776636e+18,9.28122547524785e+18,9.36389319313861e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000013,'C-R00013',7.70433183253458e+18,1.53943519529185e+18,-9.21917794935557e+18,7.63056680875309e+18,7.74579437149411e+18,1.48182141392134e+18,1.59704897666236e+18,9.16952753637058e+18,9.2847550991116e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000014,'C-R00014',7.91323176406101e+18,1.5394119054189e+18,-9.30406974807733e+18,7.89183589905819e+18,7.93325947584566e+18,1.51870011702516e+18,1.56012369381264e+18,9.28869607876491e+18,9.33011965555239e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000015,'C-R00015',7.97427471745672e+18,1.53940596743392e+18,-9.45075602574961e+18,7.93734376064457e+18,8.0190866035671e+18,1.49853454597265e+18,1.58027738889518e+18,9.41015348901336e+18,9.49189633193589e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000016,'D-R00016',7.95041209925582e+18,1.53941718859128e+18,-9.6454735723554e+18,7.90177932973845e+18,7.98120156374385e+18,1.49970607158859e+18,1.57912830559398e+18,9.59732328186493e+18,9.67674551587033e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000017,'D-R00017',7.7519840753406e+18,1.53942297029686e+18,-9.78070882037282e+18,7.74253205069414e+18,7.78656702700429e+18,1.51740548214179e+18,1.56144045845193e+18,9.74799164759289e+18,9.79202662390304e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000018,'D-R00018',7.56842521209659e+18,1.53942275948411e+18,-9.70516175784687e+18,7.52486968352331e+18,7.59782827491401e+18,1.50294346378876e+18,1.57590205517946e+18,9.67740970341119e+18,9.75036829480189e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000019,'D-R00019',7.44636914443112e+18,1.53941344668532e+18,-9.56413578288673e+18,7.40523802999379e+18,7.4893487881372e+18,1.49735806761361e+18,1.58146882575702e+18,9.51969654196901e+18,9.60380730011242e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000020,'D-R00020',7.38687628605459e+18,1.53943665746912e+18,-9.25846082480078e+18,7.33651803899941e+18,7.45065162067399e+18,1.48236986663183e+18,1.5965034483064e+18,9.19870982711003e+18,9.31284340878461e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000021,'D-R00021',7.57566761441511e+18,1.53942079142861e+18,-9.12118297000297e+18,7.53140303095667e+18,7.63573967496599e+18,1.48725246942394e+18,1.59158911343327e+18,9.07455130578647e+18,9.1788879497958e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000022,'D-R00022',7.97989002792439e+18,1.53942341490048e+18,-9.21142180614625e+18,7.92964286500324e+18,8.02579218314387e+18,1.49134875583016e+18,1.58749807397079e+18,9.16616767002207e+18,9.2623169881627e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000023,'D-R00023',8.08432384198008e+18,1.53940109532268e+18,-9.71301020294385e+18,8.03696747446986e+18,8.14337437973661e+18,1.48619764268931e+18,1.59260454795606e+18,9.65341878332365e+18,9.75982568859041e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000024,'E-R00024',7.17196241286489e+18,1.5393641347901e+18,-9.35081170884114e+18,7.11560708039207e+18,7.22923483798357e+18,1.48255025599435e+18,1.59617801358585e+18,9.29387514449117e+18,9.40750290208267e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000025,'E-R00025',7.33482171042828e+18,1.53937635072987e+18,-9.11037866197497e+18,7.30478471586242e+18,7.3523971245121e+18,1.51557014640503e+18,1.56318255505472e+18,9.08184117812085e+18,9.12945358677054e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000026,'E-R00026',7.70970984920561e+18,1.53935180604977e+18,-8.98396003844671e+18,7.64854375745104e+18,7.76794044000599e+18,1.4796534647723e+18,1.59905014732724e+18,8.92201229959521e+18,9.04140898215016e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000027,'E-R00027',8.17759165515952e+18,1.53936193256599e+18,-9.31126461189726e+18,8.13097315019258e+18,8.23328624361724e+18,1.48820538585366e+18,1.59051847927832e+18,9.26182363391695e+18,9.36413672734161e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000028,'E-R00028',8.05051276327506e+18,1.53938700010354e+18,-9.89276475978326e+18,8.00428938667613e+18,8.09610445747903e+18,1.49347946470209e+18,1.58529453550499e+18,9.84934544495731e+18,9.94116051576021e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000029,'E-R00029',7.59330303106683e+18,1.53936695954349e+18,-1.00130722972537e+19,7.56367192536078e+18,7.61213428230879e+18,1.51513578106949e+18,1.5635981380175e+18,9.9834305480215e+18,1.00318929049695e+19,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000030,'F-R00030',7.3055329070805e+18,1.53938852874337e+18,-9.83844840594144e+18,7.24186951008628e+18,7.36032502533014e+18,1.48016077112144e+18,1.5986162863653e+18,9.77764227783185e+18,9.89609779307571e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000031,'G-R00031',7.2055e+18,1.5393e+18,-9.5384e+18,7.1418e+18,7.2603e+18,1.4801e+18,1.5986e+18,9.4776e+18,9.596e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000032,'H-R00032',7.53635678280214e+18,1.5392405480114e+18,-9.74415970675043e+18,7.38635678280214e+18,7.68635678280214e+18,1.3892405480114e+18,1.6892405480114e+18,-9.89415970675043e+18,-9.59415970675043e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (11000033,'K-R00033',6.74023503982045e+18,1.53928079740317e+18,-9.71325605392788e+18,6.59023503982045e+18,6.89023503982045e+18,1.38928079740317e+18,1.68928079740317e+18,-9.86325605392788e+18,-9.56325605392788e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (12000001,'ADR01',5.3324548149311e+18,6.03270036827184e+18,-8.32466307195752e+18,5.1824548149311e+18,5.4824548149311e+18,5.88270036827184e+18,6.18270036827184e+18,-8.47466307195752e+18,-8.17466307195752e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (12000002,'ADR02',5.03404010907072e+18,4.62430246891048e+18,-9.0324568132573e+18,4.88404010907072e+18,5.18404010907072e+18,4.47430246891048e+18,4.77430246891048e+18,-9.1824568132573e+18,-8.8824568132573e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (12000003,'ADR03',6.17472571545721e+18,4.91435772600098e+18,-8.71788568137431e+18,6.02472571545721e+18,6.32472571545721e+18,4.76435772600098e+18,5.06435772600098e+18,-8.86788568137431e+18,-8.56788568137431e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (12000004,'ADR04',2.24240324043464e+18,4.81585466969463e+18,-7.55330665402802e+18,2.09240324043464e+18,2.39240324043464e+18,4.66585466969463e+18,4.96585466969463e+18,-7.70330665402802e+18,-7.40330665402802e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (12000005,'ADR05',3.56645703868574e+18,5.32733574361132e+18,-5.45724399955605e+18,3.41645703868574e+18,3.71645703868574e+18,5.17733574361132e+18,5.47733574361132e+18,-5.60724399955605e+18,-5.30724399955605e+18,NULL,NULL);
INSERT INTO "mapRegions" VALUES (13000001,'PR-01',1.00210621472284e+19,-9.40800961435039e+17,5.1871115403819e+18,9.87106214722843e+18,1.01710621472284e+19,-1.09080096143504e+18,-7.90800961435039e+17,5.0371115403819e+18,5.3371115403819e+18,NULL,NULL);

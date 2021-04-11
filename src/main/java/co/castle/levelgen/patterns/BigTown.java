package co.castle.levelgen.patterns;

import co.castle.levelgen.MonsterSpawnInfo;

public class BigTown extends StaticPattern
{
	public BigTown( )
	{
		cellMap = new String[ ][ ]
		{
			{	"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"_________________________________________________________'''''________",
				"________________________wwwwwwwww___wwwwwwwwww''________''www''_______",
				"__________''''''''''____w-------w___---------w''________'xx-ww'_______",
				"__________''''''''''____w---------__---------/''________'---dw'_______",
				"__________''''''''d'____w-------w___---------w''________'xx-ww'_______",
				"__~~~_____''''''''''____wwwwwwwww___wwwww-wwww''________''www''_______",
				"__~_~_____''''''''''____________________w-w______________'''''________",
				"__~~~___________________________________w-w___________________________",
				"________________________________________w-wwwwwwwww___________________",
				"_________wwwwwwwwwwww___________________w---------w___________________",
				"_________w--w----xx,w___________________w---------w''''''''''_________",
				"_________w--w----xx,w___________________wwwww/www-w''''''''d'_________",
				"_________wwwwwwwwww,w___________________,,,,,,,,www''''''''''_________",
				"_________w,,,,,,,,w,_____________________________________''''_________",
				"_________w,,,,,,,,w,______________________________________'''_________",
				"_________w,,,,,,,,/,______________________________________'''_________",
				"_________w,,,,,,,,w,______________________________________'''_________",
				"_________w,,,,,,,,w,______________________________________'''_________",
				"_________w,,,,,,,,w,______________________________________'''_________",
				"_________wwwwwwwwww,______________________________________wwww________",
				"______________,,,,,,______________________________________w,,w________",
				"______________,,,,,_____________________wwwwwwwwww________ww,w________",
				"_________wwwww/www._____________________w--------w______wwww,w________",
				"_________w,,,,,,,w._____________________w--------w______w,,,,w________",
				"_________w,,,,,,,www_______w''''''w_____ww-wwwwwww______w,mmmw________",
				"_________w,,,,,,,w.w_______w''''''w_____w-------w_____www/wwww________",
				"_________w,,,,,,,www_______w''''''w_____w/wwwwwwww____w------w________",
				"_________w,,,,,,,w.________w'''d''w_____w-------/w____w------w________",
				"_________wwwwwwwww.________wwwwwwww_____wwwwwwwwww____wwwwwwww________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________" },
			{	"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"________________________________________________________,,,,,,,_______",
				"_______________________________________________________,,dwwww,,______",
				"_________,,,,,,,,,,,,___wwwwwwwww___wwwwwwwwww,,_______,wwwx-ww,______",
				"_________,wwwwwwwwww,___w-------w___---------w,d_______,w--w--w,______",
				"_________,w--------w,___w---------__---------/,,_______,w-dwu-w,______",
				"_________,w-------uw,___w-------w___---------w,,_______,w--w--w,______",
				"__www____,w--------w,___wwwwwwwww___wwwww-wwww,,_______,ww---ww,______",
				"__w_w____,wwww-wwwww,___________________w-w____________,,wwwww,,______",
				"__www____,,,,,,,,,,,,___________________w-w_____________,,,,,,,_______",
				"___________________,____________________w-wwwwwwwww___________________",
				"_________wwwwwwwwww,w___________________w---------w___________________",
				"_________w--w----xx,w___________________w---------wwwwwwwwwww_________",
				"_________w--w----xx,w___________________wwwww/www-/,,,,,,,,uw_________",
				"_________wwwwwwwwww,w___________________,,,,,,,,wwwwwwwwww,,w_________",
				"_________wvvvvvvvvw,_____________________________________ww,w_________",
				"_________wvvvvvvvvw,______________________________________w,w_________",
				"_________wvvvvvvvvw,,_____________________________________w,w_________",
				"_________wvvvvvvvvw,,,,ww_________________________________w,w_________",
				"_________wvvvvvvvv/,,,,,d_________________________________w,w_________",
				"_________wvvvvvvvvw,,,,ww_________________________________w,w_________",
				"_________wwwwwwwwww,,_____________________________________w,ww________",
				"______________,,,,,,______________________________________w,,w________",
				"______________,,,,,_____________________wwwwwwwwww________ww,w________",
				"_________wwwww/www,_____________________w--------w______wwww,w________",
				"_________w,,,,,,,w,________w------w_____w--------w______,,,,,w________",
				"_________w,,,,,,,w,w_______w------w_____ww-wwwwwww______w,mmmw________",
				"_________w,,,,,,,w,d_______w------w_____w-------w_____www/wwww________",
				"_________w,,,,,,,w,w_______w------wwwwwww/wwwwwwww____w------w________",
				"_________w,,d,,d,w,________w---u--/-------------/d____-------w________",
				"_________wwwwwwwww,________wwwwwwwwwwwwwwwwwwwwwww____wwwwwwww________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________",
				"______________________________________________________________________" },
			{	"tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt",
				"tttt....ttttttto..tttttttttt......tttttt.......tttttttt..........ttttt",
				"tt..+++++++.tt+ooo++++++tt+++++ooo..++++++++++++tooott.+++++++++++o.tt",
				"tt.+++++++++++++++o+++++++++++++++oo++++++++++++++++++++wwwwwww+++++tt",
				"t.+++++++++++++++++++++++++++++++++++++++++++++++++++++wwu----ww++++.t",
				"t..++++++wwwwww/wwwww+++wwwwwwwwwmmmwwwwwwwwwwwwmmm++++w--www--w+++..t",
				"t...+++++w----------w+++w-------wmmmw---------wummm++++w-ww-ww-w+++.tt",
				"tt.++++++w----------w+++w-Y-----wmmmm=--------wwmmm++++w-wu-Yw-w++++tt",
				"t~~~~~+++w----------w+++w-------wmmmw---------wwmmm++++w-ww-ww-w+++ttt",
				"t~www~+++w----------w+++wwwwwwwwwmmmwwwwwwwwwwww=mm++++w--www--w++++tt",
				"t~wYw~+++w----------w++++++++++++++++++++++++++++++++++ww-----ww+++.tt",
				"t~www~+++wwwwwwwwwwww+++++++++++++++++++++++++++++++++++wwwwwww++++..t",
				"t~tt~~++++++++++++++++++++.....+o+++++++wwwwwwwwwww++++++++++++++++..t",
				"t~to~~+++wwwwwwwwwwww+++++t..o...t...+++w----w----w+++++++++++++++..tt",
				"t~tt~~+++w--w-------w++++.............++w----w----w+++++...++++++++ttt",
				"t~tt+~~++w--w-------w++++++.........++++w----w----w++++++wwww+++++++tt",
				"t~~t+~~~+wwwwwwwwwwww+++++...~~~~~..o+++www/wwww/ww++++++wwww+++++++.t",
				"tt~t~~~~+w------wmmwm+++...t.~~~~~..ot+++++++++++++++++++++++++++++..t",
				"tt~t+~~~+w------wmmwm++.......~~~~~..oo++++............++++++++++++++E",
				"t~~t~~~++w------wmmwm++++o...~~~~~~~~.++........o.......++++++++++++.t",
				"t~tt+++++w------wmmmm++ww.....~~~~~~~~~++++++..o...+++++++++o++++++o.t",
				"t~t++++++w------wmmwm++wu+...~~~~~....++++............+o++++++++++++.t",
				"t~t++++++w------wmmwm++ww+...t........o..........++o++++++++++++++++ot",
				"tYt++++++www/wwwwwwwm+++++......ooo+++++++++++++.o......o.++++++++.oot",
				"ttt++++++++++++++++++++++++++++++o+o++++++++++++++++++++++++++++++oott",
				"too.++++++++++++++++++++++++++++++++++++wwwwwwwwww+++++++++++++++++.tt",
				"tooo++o++wwwww/wwwwmmm+++++mmmmmmmm+++++wmmmw----w++++mmmmmw=w+++++..t",
				"t.o++o+++w--------wmmm+++++wwwwwwww+++++wmmmw----w++++mmmmmw=w+++++o.t",
				"tt.++++++w--------wwmm+++++w------/+++++wwmwwwwwww++++mmmmmmmw++++++tt",
				"tt.++++++w--------wum=+++++w------w+++++wmmmmmmmm+++++wwwwwwww++++o.tt",
				"tttt+++++wmm----mmwwmm+++++wwwwwwww+++wwwwwwwwwwwww+++w---Y--w++++++.t",
				"ttt++++++wmmu--ummwmmm+++++w------/+++w------Y--wumm++w------w+++++..t",
				"tt.++++++wwwwwwwwwwmmm+++++w------w+++wwwwwwwwwwwww+++wwwwwwww++++o.ot",
				"tt.++++++++++++++++++++++++wwwwwwww+++++++++++++++++++++++++++++++oo~t",
				"tttt++++++oo++++++++++++..++++++++++++++~~~~~~~~~++.+oo+~~~~~++oo~~~~t",
				"t~~~tS.ooo.o...tttt~~~~~~~~.tt~~~~~~~~~~~~~....~ttt~~~~~~oo~~~~~~~~~tt",
				"tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" },
			{	"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"wwwwwww*********wwwwwwwwwwwwwwwwwwwwwwwwwwwwww-----------wwwwwwwwwwwww",
				"wwwwwwwwwwww**************wwwwwwww***************--------wwwwwwwwwwwww",
				"ww***www*********************************wwwwww----------wwwwwwwwwwwww",
				"ww*-*wwwww**wwwwwwwwwwwww******************wwwwwwwwwwwwwwwwwwwwwwwwwww",
				"ww***wwww**wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"www*wwww**wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"www*www**wwwwwwwwwwwwwwwwwXwwwwwwwwwwwwwwwwwwwwwwwwwwww*****Xwwwwwwwww",
				"www*ww**wwwwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"www*ww*wwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwXww*wwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwww-*-wwwwwwwwwwwwwwwwww*-wwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwww-*********************-wwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwww-*-wwwwwwwwwwwwwww*w---wwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwww****wwwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwwwww***wwwwwwwwwwwwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww*************Wwwwwwwwww***********wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwwwww***wwwwwwwww*wwwwwwwww*wwwwwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwww****wwwwwwwwww*www----ww****wwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwww***wwwwwwwwwwwwwwwwwwww*****---wwwww*wwwwwwwwwww",
				"wX*****wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwww----wwwww*wwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwww----wwwww*wwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwww",
				"wwwwww*wwwwwwwwwwwwwwww*wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwww",
				"wwwwww**********************wwwwwwwwwwwwwwwwwwwwwwwwwwwwww*wwwwwwwwwww",
				"wwwwwwwwwwwwwwwwwwwwwwwwwww***************wwwwwwwwwwwwwwwwXwwwwwwwwwww",
				"wwwwwwwwwwwwwwwwwwwwwwwww****wwwwwwwwwwww****Xwwwwwwwwwwwwwwwwwwwwwwww",
				"wwwwwwwwwwwwwwwwwwwwwwww****wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"wwwwwwwwwwwwwwwwwwwww******wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"wwwwwwwwwwwwwwww*********wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"wwwwwwwww******************wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
				"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" } };

		inhabitants = new String[ ][ ]
		{
			{	"                                                                      ",
				"                                                                      ",
				"                                                                      ",
				"                                                                      ",
				"                                                         .....        ",
				"                        #########   ##########..        ..###..       ",
				"          ..........    #.......#   .........#..        ....##.       ",
				"          ..........    #.........  ............        .....#.       ",
				"          ..........    #.......#   .........#..        ....##.       ",
				"  ...     ..........    #########   #####.####..        ..###..       ",
				"  . .     ..........                    #.#              .....        ",
				"  ...                                   #.#                           ",
				"                                        #.#########                   ",
				"         ############                   #.........#                   ",
				"         #..#.......#                   #.........#..........         ",
				"         #..#.......#                   #####.###.#..........         ",
				"         ##########.#                   ........###..........         ",
				"         #........#.                                     ....         ",
				"         #...%....#.                                      ...         ",
				"         #..........                                      ...         ",
				"         #........#.                                      ...         ",
				"         #........#.                                      ...         ",
				"         #........#.                                      ...         ",
				"         ##########.                                      ####        ",
				"              ......                                      #..#        ",
				"              .....                     ##########        ##.#        ",
				"         #####.###.                     #........#      ####.#        ",
				"         #.......#.                     #........#      #....#        ",
				"         #.......###       #......#     ##.#######      #....#        ",
				"         #.......#.#       #......#     #.......#     ###.####        ",
				"         #.......###       #......#     #.########    #......#        ",
				"         #.......#.        #......#     #........#    #......#        ",
				"         #########.        ########     ##########    ########        ",
				"                                                                      ",
				"                                                                      ",
				"                                                                      ",
				"                                                                      " },
			{	"                                                                      ",
				"                                                                      ",
				"                                                                      ",
				"                                                        .......       ",
				"                                                       ...####..      ",
				"         ............   #########   ##########..       .##...##.      ",
				"         .##########.   #.......#   .........#..       .#..#..#.      ",
				"         .#...j....#.   #.........  ............       .#..#..#.      ",
				"         .#..........   #.......#   .........#..       .#..#..#.      ",
				"  ###    .#........#.   #########   #####.####..       .##...##.      ",
				"  # #    .####.#####.                   #.#            ..#####..      ",
				"  ###    ............                   #.#             .......       ",
				"                   .                    #.#########                   ",
				"         ##########.#                   #.........#                   ",
				"         #..#.......#                   #.........###########         ",
				"         #..#.......#                   #####.###.#.........#         ",
				"         ##########.#                   ........##########..#         ",
				"         #........#.                                     ##.#         ",
				"         #....a...#.                                      #.#         ",
				"         #p.........                                      #.#         ",
				"         #........#.   ##                                 #.#         ",
				"         #......b.#......                                 #.#         ",
				"         #........#.   ##                                 #.#         ",
				"         ##########.                                      #.##        ",
				"              ......                                      #..#        ",
				"              .....                     ##########        ##.#        ",
				"         #####.###.                     #....i...#      ####.#        ",
				"         #.......#.        #......#     #........#      .....#        ",
				"         #.......#.#       #......#     ##.#######      #....#        ",
				"         #..h....#..       #......#     #.......#     ###.####        ",
				"         #.......#.#       #......#######.########    #......#        ",
				"         #.......#.        #......................    .......#        ",
				"         #########.        #######################    ########        ",
				"                                                                      ",
				"                                                                      ",
				"                                                                      ",
				"                                                                      " },
			{	"######################################################################",
				"####....................#............##............#.................#",
				"##...................................................................#",
				"##.................9...............................5....#######......#",
				"#......................................................##.....##.....#",
				"#........######.#####...#########...############.......#..###..#.....#",
				"#........#..........#...#.....j.#...#.........#........#.##.##.#.....#",
				"#........#..........#...#.......#.........f...##.......#.#...#.#.....#",
				"##.......#..........#...#.......#...#.........##.......#.##.##.#.....#",
				"#####....#.......l..#...#########...############.......#..###..#.....#",
				"#.#.#....#....k.....#..4...............................##.....##.....#",
				"#.###....############...................................#######......#",
				"#.......................................###########..................#",
				"#........############.....#......#......#....#....#..................#",
				"#........#..#.......#...................#.C..#..D.#..................#",
				"#........#..#.......#...................#....#....#......####........#",
				"#........############...................###.####.##...e..####........#",
				"##.......#......#..#.......#.........#...............................#",
				"##.......#..m...#.g#............................e....e................",
				"#........#......#..#.................................................#",
				"#........#......#......##.....................................6......#",
				"#........#......#..#...#............2................................#",
				"#........#......#..#...##....#...............7.......................#",
				"#........####.######..................................1..............#",
				"#....................................................................#",
				"#.......................3...............##########...................#",
				"#........####.#####.....................#...#....#.........#.#.......#",
				"#........#........#........########.....#...#....#.........#.#.......#",
				"#........#........##.......#..A.........##.#######...........#.......#",
				"#........#........#........#......#.....#.............########.......#",
				"#........#....$...##.......########...############....#......#.......#",
				"#........#........#........#..B.......#.........#.....#......#.......#",
				"#........##########........#......#...############....########.......#",
				"##.........................########..................................#",
				"####......................................................8.........##",
				"#####.......................###..................................#..##",
				"######################################################################", },
			{	"######################################################################",
				"#######.........##############################.......e...#############",
				"############..............########....................e.(#############",
				"##...###.................................######.....e....#############",
				"##...#####..#############..................###########################",
				"##...####..###########################################################",
				"###.####..############################################################",
				"###.###..#################.############################......#########",
				"###.##..##################.############################.##############",
				"###.##.###################.############################.##############",
				"###.##.###################.############################.##############",
				"#####...##################.############################.##############",
				"#####......................############################.##############",
				"#####...###############.###############################.##############",
				"######.################.###############################.##############",
				"######.###############...##############################.##############",
				"######.###############...##############################.##############",
				"######.###############...######....####################.##############",
				"######.###############...########...###################.##############",
				"######.###############..............#########...........##############",
				"######.###############...########...#########.#########.##############",
				"######.###############...######....##########.###....##....###########",
				"######.###############...####################........#####.###########",
				"#......################.#########################...)#####.###########",
				"######.################.#########################....#####.###########",
				"######.################.##################################.###########",
				"######.################.##################################.###########",
				"######.################.##################################.###########",
				"######.################.##################################.###########",
				"######......................##############################.###########",
				"###########################...............################.###########",
				"#########################....############.....########################",
				"########################....##########################################",
				"#####################......###########################################",
				"################.........#############################################",
				"#########..................###########################################",
				"######################################################################" } };

		charMap.put( ".", "TOWN_GRASS" );
		charMap.put( "S", "TOWN_GRASS SOL" );
		charMap.put( "E", "BRICK_WALKWAY EXIT FOREST0" );
		charMap.put( "t", "TOWN_TREE" );
		charMap.put( "~", "TOWN_WATERWAY" );
		charMap.put( "X", "TOWN_WATERWAY_UP" );
		charMap.put( "Y", "TOWN_WATERWAY_DOWN" );
		charMap.put( "*", "TOWN_SEWER" );
		charMap.put( "o", "FOREST_DIRT" );
		charMap.put( "+", "BRICK_WALKWAY" );
		charMap.put( "w", "TOWN_WALL" );
		charMap.put( "x", "TOWN_WALL" ); // Will be breakable
		charMap.put( "m", "MIDWALKWAY" );
		charMap.put( "=", "TOWN_STAIRS" );
		charMap.put( "v", "TOWN_CHURCH_FLOOR" );
		charMap.put( "W", "STATIC_VOID EXIT SPECIAL_SEWERS_ENTRANCE0" );
		charMap.put( "_", "AIR" );
		charMap.put( "/", "TOWN_DOOR" );
		charMap.put( "s", "TOWN_DOOR_H" );
		charMap.put( "-", "HOUSE_FLOOR" );
		charMap.put( "u", "TOWN_STAIRSUP" );
		charMap.put( "d", "TOWN_STAIRSDOWN" );
		/* charMap.put(",", "BRICK_WALKWAY2"); */
		charMap.put( ",", "BRICK_WALKWAY" );
		charMap.put( "'", "TOWN_ROOF" );

		inhabitantsMap.put( "A", "MERCHANT 1" );
		inhabitantsMap.put( "B", "MERCHANT 2" );
		inhabitantsMap.put( "C", "MERCHANT 3" );
		inhabitantsMap.put( "D", "MERCHANT 4" );
		inhabitantsMap.put( "1", "NPC MAN0" );
		inhabitantsMap.put( "2", "NPC MAN1" );
		inhabitantsMap.put( "3", "NPC MAN2" );
		inhabitantsMap.put( "4", "NPC WOMAN0" );
		inhabitantsMap.put( "5", "NPC WOMAN1" );
		inhabitantsMap.put( "6", "NPC WOMAN2" );
		inhabitantsMap.put( "7", "NPC OLDMAN0" );
		inhabitantsMap.put( "8", "NPC OLDMAN1" );
		inhabitantsMap.put( "9", "NPC OLDMAN2" );
		inhabitantsMap.put( "a", "NPC OLDWOMAN0" );
		inhabitantsMap.put( "b", "NPC OLDWOMAN1" );
		inhabitantsMap.put( "c", "NPC OLDWOMAN2" );
		inhabitantsMap.put( "p", "NPC PRIEST" );
		inhabitantsMap.put( "e", "NPC DOG" );
		inhabitantsMap.put( "f", "NPC ICEY" );
		inhabitantsMap.put( "g", "NPC LARDA" );
		inhabitantsMap.put( "$", "NPC ELI" );
		inhabitantsMap.put( "h", "NPC MAN3" );
		inhabitantsMap.put( "i", "NPC MAN4" );
		inhabitantsMap.put( "j", "NPC WOMAN3" );
		inhabitantsMap.put( "k", "NPC WOMAN4" );
		inhabitantsMap.put( "l", "NPC WOMAN5" );
		inhabitantsMap.put( "m", "NPC BARRY" );
		inhabitantsMap.put( "%", "NPC GRAM" );
		inhabitantsMap.put( "(", "NPC CUILOT" );
		inhabitantsMap.put( ")", "NPC LIONARD" );

		spawnInfo = new MonsterSpawnInfo[ ]
		{	new MonsterSpawnInfo( "GZOMBIE", MonsterSpawnInfo.UNDERGROUND, 95 ),
			new MonsterSpawnInfo( "BAT", MonsterSpawnInfo.UNDERGROUND, 95 ), };
	}

	public String getDescription( )
	{
		return "Petra Town";
	}

	public String getMapKey( )
	{
		return "TOWN";
	}

	public String getMusicKeyMorning( )
	{
		return "DAY_TOWN";
	}

	public String getMusicKeyNoon( )
	{
		return "NIGHT_TRANSYLVANIA";
	}

	public boolean isHaunted( )
	{
		return true;
	}

	public boolean isHostageSafe( )
	{
		return true;
	}
}
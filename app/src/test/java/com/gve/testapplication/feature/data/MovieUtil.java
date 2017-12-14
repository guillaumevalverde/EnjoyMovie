package com.gve.testapplication.feature.data;

import com.google.gson.Gson;
import com.gve.testapplication.feature.presentation.MovieRaw;
import com.gve.testapplication.feature.presentation.MoviesPage;

/**
 * Created by gve on 14/12/2017.
 */

public class MovieUtil {

    public static String MOVIE_1_JSON = "{\n" +
            "      \"poster_path\": \"/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.\",\n" +
            "      \"release_date\": \"2014-10-10\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        10402\n" +
            "      ],\n" +
            "      \"id\": 244786,\n" +
            "      \"original_title\": \"Whiplash\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Whiplash\",\n" +
            "      \"backdrop_path\": \"/6bbZ6XyvgfjhQwbplnUh1LSj1ky.jpg\",\n" +
            "      \"popularity\": 10.776056,\n" +
            "      \"vote_count\": 2059,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.29\n" +
            "    }";

    public static String RAW_JSON_PAGE = "{\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"poster_path\": \"/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.\",\n" +
            "      \"release_date\": \"1994-09-10\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 278,\n" +
            "      \"original_title\": \"The Shawshank Redemption\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"The Shawshank Redemption\",\n" +
            "      \"backdrop_path\": \"/xBKGJQsAIeweesB79KC89FpBrVr.jpg\",\n" +
            "      \"popularity\": 6.741296,\n" +
            "      \"vote_count\": 5238,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.32\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.\",\n" +
            "      \"release_date\": \"2014-10-10\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        10402\n" +
            "      ],\n" +
            "      \"id\": 244786,\n" +
            "      \"original_title\": \"Whiplash\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Whiplash\",\n" +
            "      \"backdrop_path\": \"/6bbZ6XyvgfjhQwbplnUh1LSj1ky.jpg\",\n" +
            "      \"popularity\": 10.776056,\n" +
            "      \"vote_count\": 2059,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.29\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/d4KNaTrltq6bpkFS01pYtyXa09m.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"The story spans the years from 1945 to 1955 and chronicles the fictional Italian-American Corleone crime family. When organized crime family patriarch Vito Corleone barely survives an attempt on his life, his youngest son, Michael, steps in to take care of the would-be killers, launching a campaign of bloody revenge.\",\n" +
            "      \"release_date\": \"1972-03-15\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 238,\n" +
            "      \"original_title\": \"The Godfather\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"The Godfather\",\n" +
            "      \"backdrop_path\": \"/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg\",\n" +
            "      \"popularity\": 4.554654,\n" +
            "      \"vote_count\": 3570,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.26\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/ynXoOxmDHNQ4UAy0oU6avW71HVW.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?\",\n" +
            "      \"release_date\": \"2001-07-20\",\n" +
            "      \"genre_ids\": [\n" +
            "        14,\n" +
            "        12,\n" +
            "        16,\n" +
            "        10751\n" +
            "      ],\n" +
            "      \"id\": 129,\n" +
            "      \"original_title\": \"千と千尋の神隠し\",\n" +
            "      \"original_language\": \"ja\",\n" +
            "      \"title\": \"Spirited Away\",\n" +
            "      \"backdrop_path\": \"/djgM2d3e42p9GFQObg6lwK2SVw2.jpg\",\n" +
            "      \"popularity\": 6.886678,\n" +
            "      \"vote_count\": 2000,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.15\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.\",\n" +
            "      \"release_date\": \"2014-11-05\",\n" +
            "      \"genre_ids\": [\n" +
            "        12,\n" +
            "        18,\n" +
            "        878\n" +
            "      ],\n" +
            "      \"id\": 157336,\n" +
            "      \"original_title\": \"Interstellar\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Interstellar\",\n" +
            "      \"backdrop_path\": \"/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg\",\n" +
            "      \"popularity\": 12.481061,\n" +
            "      \"vote_count\": 5600,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.12\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/tHbMIIF51rguMNSastqoQwR0sBs.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"The continuing saga of the Corleone crime family tells the story of a young Vito Corleone growing up in Sicily and in 1910s New York; and follows Michael Corleone in the 1950s as he attempts to expand the family business into Las Vegas, Hollywood and Cuba\",\n" +
            "      \"release_date\": \"1974-12-20\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 240,\n" +
            "      \"original_title\": \"The Godfather: Part II\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"The Godfather: Part II\",\n" +
            "      \"backdrop_path\": \"/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg\",\n" +
            "      \"popularity\": 4.003715,\n" +
            "      \"vote_count\": 1894,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.1\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/4mFsNQwbD0F237Tx7gAPotd0nbJ.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A true story of two men who should never have met - a quadriplegic aristocrat who was injured in a paragliding accident and a young man from the projects.\",\n" +
            "      \"release_date\": \"2011-11-02\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        35\n" +
            "      ],\n" +
            "      \"id\": 77338,\n" +
            "      \"original_title\": \"Intouchables\",\n" +
            "      \"original_language\": \"fr\",\n" +
            "      \"title\": \"The Intouchables\",\n" +
            "      \"backdrop_path\": \"/ihWaJZCUIon2dXcosjQG2JHJAPN.jpg\",\n" +
            "      \"popularity\": 3.698279,\n" +
            "      \"vote_count\": 2740,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.1\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/bwVhmPpydv8P7mWfrmL3XVw0MV5.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"In the latter part of World War II, a boy and his sister, orphaned when their mother is killed in the firebombing of Tokyo, are left to survive on their own in what remains of civilian life in Japan. The plot follows this boy and his sister as they do their best to survive in the Japanese countryside, battling hunger, prejudice, and pride in their own quiet, personal battle.\",\n" +
            "      \"release_date\": \"1988-04-16\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        18,\n" +
            "        10751,\n" +
            "        10752\n" +
            "      ],\n" +
            "      \"id\": 12477,\n" +
            "      \"original_title\": \"火垂るの墓\",\n" +
            "      \"original_language\": \"ja\",\n" +
            "      \"title\": \"Grave of the Fireflies\",\n" +
            "      \"backdrop_path\": \"/fCUIuG7y4YKC3hofZ8wsj7zhCpR.jpg\",\n" +
            "      \"popularity\": 1.001401,\n" +
            "      \"vote_count\": 430,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.07\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/yPisjyLweCl1tbgwgtzBCNCBle.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Told from the perspective of businessman Oskar Schindler who saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory. Schindler’s List is based on a true story, illustrated in black and white and controversially filmed in many original locations.\",\n" +
            "      \"release_date\": \"1993-11-29\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        36,\n" +
            "        10752\n" +
            "      ],\n" +
            "      \"id\": 424,\n" +
            "      \"original_title\": \"Schindler's List\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Schindler's List\",\n" +
            "      \"backdrop_path\": \"/rIpSszng8P0DL0TimSzZbpfnvh1.jpg\",\n" +
            "      \"popularity\": 5.372319,\n" +
            "      \"vote_count\": 2308,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.07\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/eqFckcHuFCT1FrzLOAvXBb4jHwq.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Jack is a young boy of 5 years old who has lived all his life in one room. He believes everything within it are the only real things in the world. But what will happen when his Ma suddenly tells him that there are other things outside of Room?\",\n" +
            "      \"release_date\": \"2015-10-16\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 264644,\n" +
            "      \"original_title\": \"Room\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Room\",\n" +
            "      \"backdrop_path\": \"/tBhp8MGaiL3BXpPCSl5xY397sGH.jpg\",\n" +
            "      \"popularity\": 5.593128,\n" +
            "      \"vote_count\": 1179,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.06\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/f7DImXDebOs148U4uPjI61iDvaK.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.\",\n" +
            "      \"release_date\": \"1997-12-20\",\n" +
            "      \"genre_ids\": [\n" +
            "        35,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 637,\n" +
            "      \"original_title\": \"La vita è bella\",\n" +
            "      \"original_language\": \"it\",\n" +
            "      \"title\": \"Life Is Beautiful\",\n" +
            "      \"backdrop_path\": \"/bORe0eI72D874TMawOOFvqWS6Xe.jpg\",\n" +
            "      \"popularity\": 5.385594,\n" +
            "      \"vote_count\": 1593,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.06\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/s0C78plmx3dFcO3WMnoXCz56FiN.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A boy growing up in Dublin during the 1980s escapes his strained family life by starting a band to impress the mysterious girl he likes.\",\n" +
            "      \"release_date\": \"2016-04-15\",\n" +
            "      \"genre_ids\": [\n" +
            "        10749,\n" +
            "        18,\n" +
            "        10402\n" +
            "      ],\n" +
            "      \"id\": 369557,\n" +
            "      \"original_title\": \"Sing Street\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Sing Street\",\n" +
            "      \"backdrop_path\": \"/9j4UaRypr19wz0BOofwvkPRm1Se.jpg\",\n" +
            "      \"popularity\": 3.343073,\n" +
            "      \"vote_count\": 61,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.06\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.\",\n" +
            "      \"release_date\": \"2008-07-16\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        28,\n" +
            "        80,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 155,\n" +
            "      \"original_title\": \"The Dark Knight\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"The Dark Knight\",\n" +
            "      \"backdrop_path\": \"/nnMC0BM6XbjIIrT4miYmMtPGcQV.jpg\",\n" +
            "      \"popularity\": 8.090715,\n" +
            "      \"vote_count\": 7744,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.06\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/811DjJTon9gD6hZ8nCjSitaIXFQ.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.\",\n" +
            "      \"release_date\": \"1999-10-14\",\n" +
            "      \"genre_ids\": [\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 550,\n" +
            "      \"original_title\": \"Fight Club\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Fight Club\",\n" +
            "      \"backdrop_path\": \"/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg\",\n" +
            "      \"popularity\": 6.590102,\n" +
            "      \"vote_count\": 5221,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.05\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/dM2w364MScsjFf8pfMbaWUcWrR.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.\",\n" +
            "      \"release_date\": \"1994-10-14\",\n" +
            "      \"genre_ids\": [\n" +
            "        53,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 680,\n" +
            "      \"original_title\": \"Pulp Fiction\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Pulp Fiction\",\n" +
            "      \"backdrop_path\": \"/mte63qJaVnoxkkXbHkdFujBnBgd.jpg\",\n" +
            "      \"popularity\": 7.760216,\n" +
            "      \"vote_count\": 4722,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.04\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/gzlJkVfWV5VEG5xK25cvFGJgkDz.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Ashitaka, a prince of the disappearing Ainu tribe, is cursed by a demonized boar god and must journey to the west to find a cure. Along the way, he encounters San, a young human woman fighting to protect the forest, and Lady Eboshi, who is trying to destroy it. Ashitaka must find a way to bring balance to this conflict.\",\n" +
            "      \"release_date\": \"1997-07-12\",\n" +
            "      \"genre_ids\": [\n" +
            "        12,\n" +
            "        14,\n" +
            "        16\n" +
            "      ],\n" +
            "      \"id\": 128,\n" +
            "      \"original_title\": \"もののけ姫\",\n" +
            "      \"original_language\": \"ja\",\n" +
            "      \"title\": \"Princess Mononoke\",\n" +
            "      \"backdrop_path\": \"/dB2rATwfCbsPGfRLIoluBnKdVHb.jpg\",\n" +
            "      \"popularity\": 4.672361,\n" +
            "      \"vote_count\": 954,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.04\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/3TpMBcAYH4cxCw5WoRacWodMTCG.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"An urban office worker finds that paper airplanes are instrumental in meeting a girl in ways he never expected.\",\n" +
            "      \"release_date\": \"2012-11-02\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        10751,\n" +
            "        10749\n" +
            "      ],\n" +
            "      \"id\": 140420,\n" +
            "      \"original_title\": \"Paperman\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Paperman\",\n" +
            "      \"backdrop_path\": \"/cqn1ynw78Wan37jzs1Ckm7va97G.jpg\",\n" +
            "      \"popularity\": 2.907096,\n" +
            "      \"vote_count\": 452,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.03\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/pwpGfTImTGifEGgLb3s6LRPd4I6.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Henry Hill is a small time gangster, who takes part in a robbery with Jimmy Conway and Tommy De Vito, two other gangsters who have set their sights a bit higher. His two partners kill off everyone else involved in the robbery, and slowly start to climb up through the hierarchy of the Mob. Henry, however, is badly affected by his partners success, but will he stoop low enough to bring about the downfall of Jimmy and Tommy?\",\n" +
            "      \"release_date\": \"1990-09-12\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 769,\n" +
            "      \"original_title\": \"Goodfellas\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Goodfellas\",\n" +
            "      \"backdrop_path\": \"/xDEOxA01480uLTWuvQCw61VmDBt.jpg\",\n" +
            "      \"popularity\": 3.783589,\n" +
            "      \"vote_count\": 1528,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.02\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/z4ROnCrL77ZMzT0MsNXY5j25wS2.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A man with a low IQ has accomplished great things in his life and been present during significant historic events - in each case, far exceeding what anyone imagined he could do. Yet, despite all the things he has attained, his one true love eludes him. 'Forrest Gump' is the story of a man who rose above his challenges, and who proved that determination, courage, and love are more important than ability.\",\n" +
            "      \"release_date\": \"1994-07-06\",\n" +
            "      \"genre_ids\": [\n" +
            "        35,\n" +
            "        18,\n" +
            "        10749\n" +
            "      ],\n" +
            "      \"id\": 13,\n" +
            "      \"original_title\": \"Forrest Gump\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Forrest Gump\",\n" +
            "      \"backdrop_path\": \"/ctOEhQiFIHWkiaYp7b0ibSTe5IL.jpg\",\n" +
            "      \"popularity\": 6.224491,\n" +
            "      \"vote_count\": 4279,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.02\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/5hqbJSmtAimbaP3XcYshCixuUtk.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"A veteran samurai, who has fallen on hard times, answers a village's request for protection from bandits. He gathers 6 other samurai to help him, and they teach the townspeople how to defend themselves, and they supply the samurai with three small meals a day. The film culminates in a giant battle when 40 bandits attack the village.\",\n" +
            "      \"release_date\": \"1954-04-26\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 346,\n" +
            "      \"original_title\": \"七人の侍\",\n" +
            "      \"original_language\": \"ja\",\n" +
            "      \"title\": \"Seven Samurai\",\n" +
            "      \"backdrop_path\": \"/61vLiK96sbXeHpQiMxI4CuqBA3z.jpg\",\n" +
            "      \"popularity\": 2.93856,\n" +
            "      \"vote_count\": 436,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.02\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total_results\": 5206,\n" +
            "  \"total_pages\": 261\n" +
            "}";

    public static MovieRaw getMovieRaw(Gson gson) {
        return gson.fromJson(MovieUtil.MOVIE_1_JSON, MovieRaw.class);
    }

    public static MoviesPage getMoviesInPage(Gson gson) {
        return gson.fromJson(MovieUtil.RAW_JSON_PAGE, MoviesPage.class);
    }
}

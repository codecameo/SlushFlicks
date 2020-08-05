package com.sifat.slushflicks.utils.api

const val tvCastResponse = """
{
  "cast": [
    {
      "character": "Walter White",
      "credit_id": "52542282760ee313280017f9",
      "id": 17419,
      "name": "Bryan Cranston",
      "gender": 2,
      "profile_path": "/aGSvZg7uITJveQtGHDcPNI6map1.jpg",
      "order": 0
    },
    {
      "character": "Jesse Pinkman",
      "credit_id": "52542282760ee31328001845",
      "id": 84497,
      "name": "Aaron Paul",
      "gender": 2,
      "profile_path": "/u8UdsB9yenM4uHEjmce4nkBn48X.jpg",
      "order": 1
    },
    {
      "character": "Skyler White",
      "credit_id": "52542282760ee3132800181b",
      "id": 134531,
      "name": "Anna Gunn",
      "gender": 1,
      "profile_path": "/adppyeu1a4REN3khtgmXusrapFi.jpg",
      "order": 2
    },
    {
      "character": "Hank Schrader",
      "credit_id": "52542283760ee3132800187b",
      "id": 14329,
      "name": "Dean Norris",
      "gender": 2,
      "profile_path": "/yV3DZ52LYRf5F605xRas5BEcJrZ.jpg",
      "order": 3
    },
    {
      "character": "Marie Schrader",
      "credit_id": "52542283760ee31328001891",
      "id": 1217934,
      "name": "Betsy Brandt",
      "gender": 1,
      "profile_path": "/zmhhPmXnwjSzVyoTVL93i1EkLRK.jpg",
      "order": 4
    },
    {
      "character": "Walter White Jr.",
      "credit_id": "52542284760ee313280018a9",
      "id": 209674,
      "name": "RJ Mitte",
      "gender": 2,
      "profile_path": "/aG6NYV2EgzBFLRIl7vvbtd7go1j.jpg",
      "order": 5
    },
    {
      "character": "Saul Goodman",
      "credit_id": "5271b180760ee35afc09bb8d",
      "id": 59410,
      "name": "Bob Odenkirk",
      "gender": 2,
      "profile_path": "/rF0Lb6SBhGSTvjRffmlKRSeI3jE.jpg",
      "order": 6
    },
    {
      "character": "Mike Ehrmantraut",
      "credit_id": "5271b1e6760ee35af60941ad",
      "id": 783,
      "name": "Jonathan Banks",
      "gender": 2,
      "profile_path": "/va6APAzwv68YxvYQkB3lHhpccCi.jpg",
      "order": 7
    },
    {
      "character": "Walter White",
      "credit_id": "52542282760ee313280017f9",
      "id": 17419,
      "name": "Bryan Cranston",
      "gender": 2,
      "profile_path": "/aGSvZg7uITJveQtGHDcPNI6map1.jpg",
      "order": 8
    },
    {
      "character": "Jesse Pinkman",
      "credit_id": "52542282760ee31328001845",
      "id": 84497,
      "name": "Aaron Paul",
      "gender": 2,
      "profile_path": "/u8UdsB9yenM4uHEjmce4nkBn48X.jpg",
      "order": 9
    },
    {
      "character": "Skyler White",
      "credit_id": "52542282760ee3132800181b",
      "id": 134531,
      "name": "Anna Gunn",
      "gender": 1,
      "profile_path": "/adppyeu1a4REN3khtgmXusrapFi.jpg",
      "order": 10
    },
    {
      "character": "Hank Schrader",
      "credit_id": "52542283760ee3132800187b",
      "id": 14329,
      "name": "Dean Norris",
      "gender": 2,
      "profile_path": "/yV3DZ52LYRf5F605xRas5BEcJrZ.jpg",
      "order": 11
    },
    {
      "character": "Marie Schrader",
      "credit_id": "52542283760ee31328001891",
      "id": 1217934,
      "name": "Betsy Brandt",
      "gender": 1,
      "profile_path": "/zmhhPmXnwjSzVyoTVL93i1EkLRK.jpg",
      "order": 12
    },
    {
      "character": "Walter White Jr.",
      "credit_id": "52542284760ee313280018a9",
      "id": 209674,
      "name": "RJ Mitte",
      "gender": 2,
      "profile_path": "/aG6NYV2EgzBFLRIl7vvbtd7go1j.jpg",
      "order": 13
    },
    {
      "character": "Saul Goodman",
      "credit_id": "5271b180760ee35afc09bb8d",
      "id": 59410,
      "name": "Bob Odenkirk",
      "gender": 2,
      "profile_path": "/rF0Lb6SBhGSTvjRffmlKRSeI3jE.jpg",
      "order": 14
    },
    {
      "character": "Mike Ehrmantraut",
      "credit_id": "5271b1e6760ee35af60941ad",
      "id": 783,
      "name": "Jonathan Banks",
      "gender": 2,
      "profile_path": "/va6APAzwv68YxvYQkB3lHhpccCi.jpg",
      "order": 15
    }
  ],
  "id": 1396
}
"""

const val tvVideoResponse = """
{
  "id": 60735,
  "results": [
    {
      "id": "5c9296fd0e0a267cdd168c57",
      "iso_639_1": "en",
      "iso_3166_1": "US",
      "key": "Yj0l7iGKh8g",
      "name": "The Flash | Extended Trailer | The CW",
      "site": "YouTube",
      "size": 1080,
      "type": "Trailer"
    },
    {
      "id": "552d7e27c3a368750100138b",
      "iso_639_1": "en",
      "iso_3166_1": "US",
      "key": "Mx7xTF8fKz4",
      "name": "THE FLASH 2014 T.V. SERIES INTRO",
      "site": "YouTube",
      "size": 360,
      "type": "Opening Credits"
    }
  ]
}
"""

const val tvNoVideoResponse = """
{
  "id": 60735,
  "results": [
    {
      "id": "5c9296fd0e0a267cdd168c57",
      "iso_639_1": "en",
      "iso_3166_1": "US",
      "key": "Yj0l7iGKh8g",
      "name": "The Flash | Extended Trailer | The CW",
      "site": "Insta",
      "size": 1080,
      "type": "Teaser"
    },
    {
      "id": "552d7e27c3a368750100138b",
      "iso_639_1": "en",
      "iso_3166_1": "US",
      "key": "Mx7xTF8fKz4",
      "name": "THE FLASH 2014 T.V. SERIES INTRO",
      "site": "YouTube",
      "size": 360,
      "type": "Opening Credits"
    }
  ]
}
"""

const val tvSimilarResponse = """
{
  "page": 1,
  "results": [
    {
      "backdrop_path": "/qkX4DLviNAFpopFu98RGbpBQSGy.jpg",
      "first_air_date": "2014-06-27",
      "genre_ids": [
        35,
        10751
      ],
      "id": 46028,
      "name": "Girl Meets World",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Girl Meets World",
      "overview": "The adventures of relatable and adventurous Riley Matthews, the tween daughter of Cory and Topanga Matthews, and her bold best friend Maya as they traverse the twists and turns of teenage years at Manhattan's John Quincy Adams Middle School where Riley's dad is their History teacher.",
      "poster_path": "/oU8SXLKORjuSWlszJTFe7GG8r9n.jpg",
      "vote_average": 7.6,
      "vote_count": 104,
      "popularity": 17.748
    },
    {
      "backdrop_path": null,
      "first_air_date": "1992-09-23",
      "genre_ids": [
        35
      ],
      "id": 500,
      "name": "Mad About You",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Mad About You",
      "overview": "Young, urban newlyweds Paul and Jamie Buchman try to sustain their marital bliss while sidestepping the hurdles of love in the '90s.",
      "poster_path": "/vvnslQjc1LnERuroGVh8bPdBi2r.jpg",
      "vote_average": 6.4,
      "vote_count": 97,
      "popularity": 15.755
    },
    {
      "original_name": "The Nanny",
      "id": 2352,
      "name": "The Nanny",
      "vote_count": 204,
      "vote_average": 7.1,
      "first_air_date": "1993-11-03",
      "poster_path": "/3hB4Ddekwl8FsqFZvrp1xIMbKgI.jpg",
      "genre_ids": [
        35
      ],
      "original_language": "en",
      "backdrop_path": "/g9WaKT7n5PjxL9wbpbzGFmuvvWa.jpg",
      "overview": "Fran, fresh out of her job as a bridal consultant in her boyfriend’s shop, first appears on the doorstep of Broadway producer Maxwell Sheffield peddling cosmetics, and quickly stumbled upon the opportunity to become The Nanny for his three children. But soon Fran, with her offbeat nurturing and no-nonsense honesty, touches Maxwell as well as the kids.",
      "origin_country": [
        "US"
      ],
      "popularity": 31.252
    },
    {
      "original_name": "2 Broke Girls",
      "id": 39340,
      "name": "2 Broke Girls",
      "vote_count": 667,
      "vote_average": 6.5,
      "first_air_date": "2011-09-19",
      "poster_path": "/iQmBh4rmOpL7HCADqBmwkB9chIT.jpg",
      "genre_ids": [
        35
      ],
      "original_language": "en",
      "backdrop_path": "/hiGp7DQiEXxDCqAfhIIPbYuQ28U.jpg",
      "overview": "Comedy about the unlikely friendship that develops between two very different young women who meet waitressing at a diner in trendy Williamsburg, Brooklyn, and form a bond over one day owning their own successful cupcake business. Only one thing stands in their way – they’re broke.",
      "origin_country": [
        "US"
      ],
      "popularity": 31.467
    },
    {
      "backdrop_path": "/5DciiXYnau5iztSEihOdxUuUAKr.jpg",
      "first_air_date": "1998-06-06",
      "genre_ids": [
        35
      ],
      "id": 105,
      "name": "Sex and the City",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Sex and the City",
      "overview": "Based on the bestselling book by Candace Bushnell, Sex and the City tells the story of four best friends, all single and in their late thirties, as they pursue their careers and talk about their sex lives, all while trying to survive the New York social scene. ",
      "poster_path": "/yd5tIz125kF25H4Okld3uqyQgvA.jpg",
      "vote_average": 6.8,
      "vote_count": 661,
      "popularity": 35.026
    },
    {
      "original_name": "Will & Grace",
      "id": 4454,
      "name": "Will & Grace",
      "vote_count": 209,
      "vote_average": 6.9,
      "first_air_date": "1998-09-21",
      "poster_path": "/m2AWZDAHesXDmLwVm73BpGlP55a.jpg",
      "genre_ids": [
        35
      ],
      "original_language": "en",
      "backdrop_path": "/pIPMPMEZErCxTvQG9HDt4AsnAfn.jpg",
      "overview": "Will Truman and Grace Adler are best friends living in New York, and when Grace's engagement falls apart, she moves in with Will. Together, along with their friends, they go through the trials of dating, sex, relationships and their careers, butting heads at times but ultimately supporting one another while exchanging plenty of witty banter along the way.",
      "origin_country": [
        "US"
      ],
      "popularity": 33.95
    },
    {
      "original_name": "Friends",
      "id": 1668,
      "name": "Friends",
      "vote_count": 2559,
      "vote_average": 8.1,
      "first_air_date": "1994-09-22",
      "poster_path": "/f496cm9enuEsZkSPzCwnTESEK5s.jpg",
      "genre_ids": [
        35,
        18
      ],
      "original_language": "en",
      "backdrop_path": "/qjUqBVLUPX1Pc4cyYljZfAJHf8N.jpg",
      "overview": "The misadventures of a group of friends as they navigate the pitfalls of work, life and love in Manhattan.",
      "origin_country": [
        "US"
      ],
      "popularity": 84.962
    },
    {
      "backdrop_path": "/eyi5tMbK6fRtXNFffUTIhRiX0LT.jpg",
      "first_air_date": "1996-09-17",
      "genre_ids": [
        35
      ],
      "id": 2359,
      "name": "Spin City",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Spin City",
      "overview": "Workaholic Mike Flaherty is the Deputy Mayor of New York City, serving as Mayor Randall Winston's key strategist and much-needed handler. Mike runs the city with the help of his oddball staff: an anxious and insecure press secretary; a sexist, boorish chief of staff; an impeccably groomed gay activist running minority affairs; a sharp and efficient, man-crazy accountant; and an idealistic young speechwriter. Like Mike, they are all professionally capable but personally challenged.",
      "poster_path": "/iuBzOwnUNyce4KVnCDXphFOzv6i.jpg",
      "vote_average": 7.2,
      "vote_count": 128,
      "popularity": 15.529
    },
    {
      "original_name": "30 Rock",
      "id": 4608,
      "name": "30 Rock",
      "vote_count": 355,
      "vote_average": 7.2,
      "first_air_date": "2006-10-11",
      "poster_path": "/5nunOMIVE79oBzRXfHPFpvRPwHt.jpg",
      "genre_ids": [
        35
      ],
      "original_language": "en",
      "backdrop_path": "/cK5AbLFBY2JDoqEdVXk0697e2SV.jpg",
      "overview": "30 Rock is an American television comedy series that ran on NBC from October 11, 2006, to January 31, 2013, and was created by Tina Fey. The series, which is loosely based on Fey's experiences as head writer for Saturday Night Live, takes place behind the scenes of a fictional live sketch comedy series depicted as airing on NBC. The series' name refers to 30 Rockefeller Plaza in New York City, the address of the GE Building, in which the NBC Studios are located.",
      "origin_country": [
        "US"
      ],
      "popularity": 25.772
    },
    {
      "backdrop_path": "/koWH7LZWZ8zUmr1rNKDhMWdkr99.jpg",
      "first_air_date": "2014-01-22",
      "genre_ids": [
        35
      ],
      "id": 60839,
      "name": "Broad City",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Broad City",
      "overview": "Broad City follows two women throughout their daily lives in New York City, making the smallest and mundane events hysterical and disturbing to watch all at the same time.",
      "poster_path": "/9w8UKR9ZoIhabAvmRhI1KhV4Ngy.jpg",
      "vote_average": 7.5,
      "vote_count": 97,
      "popularity": 12.618
    },
    {
      "backdrop_path": "/nzDvr0AkXWTHgvSRVgUVosKVgOh.jpg",
      "first_air_date": "2015-03-06",
      "genre_ids": [
        35
      ],
      "id": 61671,
      "name": "Unbreakable Kimmy Schmidt",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Unbreakable Kimmy Schmidt",
      "overview": "When a woman is rescued from a doomsday cult and lands in New York City, she must navigate a world she didn’t think even existed anymore.",
      "poster_path": "/eMFHl0HyLg7k04OVeE4xPvT6XhY.jpg",
      "vote_average": 7.3,
      "vote_count": 254,
      "popularity": 19.174
    },
    {
      "backdrop_path": "/lyTCCuymqg8egIaQXZAY5vE4MB0.jpg",
      "first_air_date": "1989-07-05",
      "genre_ids": [
        35
      ],
      "id": 1400,
      "name": "Seinfeld",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Seinfeld",
      "overview": "A stand-up comedian and his three offbeat friends weather the pitfalls and payoffs of life in New York City in the '90s. It's a show about nothing.",
      "poster_path": "/aCw8ONfyz3AhngVQa1E2Ss4KSUQ.jpg",
      "vote_average": 8.3,
      "vote_count": 834,
      "popularity": 32.712
    },
    {
      "original_name": "The King of Queens",
      "id": 4238,
      "name": "The King of Queens",
      "vote_count": 310,
      "vote_average": 7,
      "first_air_date": "1998-09-21",
      "poster_path": "/fGsnwNz2ZANPn9AESumxZOmh1fU.jpg",
      "genre_ids": [
        35
      ],
      "original_language": "en",
      "backdrop_path": "/xF4zSVyrOBO60Lwu2wDN0dBjEgk.jpg",
      "overview": "Three's definitely a crowd for parcel post deliveryman Doug Heffernan, whose newly widowed father-in-law, Arthur has moved in with him and his wife Carrie. Doug's no longer the king of his domain -- the renovated basement that houses his beloved supersized TV set -- let alone the king of Queens, where he lives.",
      "origin_country": [
        "US"
      ],
      "popularity": 24.797
    },
    {
      "original_name": "Brooklyn Nine-Nine",
      "id": 48891,
      "name": "Brooklyn Nine-Nine",
      "vote_count": 1065,
      "vote_average": 7.9,
      "first_air_date": "2013-09-17",
      "poster_path": "/dzj0oLZWe3qMgK4jlgdKWPVxxIx.jpg",
      "genre_ids": [
        35,
        80
      ],
      "original_language": "en",
      "backdrop_path": "/ncC9ZgZuKOdaVm7yXinUn26Qyok.jpg",
      "overview": "A single-camera ensemble comedy following the lives of an eclectic group of detectives in a New York precinct, including one slacker who is forced to shape up when he gets a new boss.",
      "origin_country": [
        "US"
      ],
      "popularity": 57.855
    },
    {
      "backdrop_path": "/yr73Vb6V0sTygpTVIpBd0zDRDj.jpg",
      "first_air_date": "2015-03-31",
      "genre_ids": [
        18,
        35
      ],
      "id": 62117,
      "name": "Younger",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Younger",
      "overview": "Liza Miller, a suddenly single stay-at-home mother, tries to get back into the working world, only to find it’s nearly impossible to start at the bottom at 40-year old. When a chance encounter convinces her she looks younger than she is, Liza tries to pass herself off as 26 and lands a job as an assistant at Empirical Press. Now she just has to make sure no one finds out the secret only she and her best friend Maggie share.",
      "poster_path": "/mF1g0J0EKSrxnYMr5t9uAzBbJIa.jpg",
      "vote_average": 6.3,
      "vote_count": 94,
      "popularity": 19.097
    },
    {
      "backdrop_path": "/rE7TyAqUUIYe2m07t1bOaUaGYVX.jpg",
      "first_air_date": "2012-06-20",
      "genre_ids": [
        35
      ],
      "id": 42272,
      "name": "Baby Daddy",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Baby Daddy",
      "overview": "Baby Daddy follows Ben, a young man in his early 20s living the life of a bachelor in New York City with his buddy, Tucker, and his brother, Danny. Their lives are turned upside down when they come home one day to find a baby girl left on the doorstep by an ex-girlfriend of Ben's. After much deliberation, Ben decides to raise the baby with the help of his friends and his protective and sometimes over-bearing mother, Bonnie, and his close female friend, Riley.",
      "poster_path": "/lwpaNYFacUITOtfIZ1UDq7vnKeD.jpg",
      "vote_average": 6.7,
      "vote_count": 103,
      "popularity": 18.739
    },
    {
      "backdrop_path": "/x6rj0QZgLnWwIoJDDTyEg2TCIv1.jpg",
      "first_air_date": "1978-09-12",
      "genre_ids": [
        35
      ],
      "id": 2251,
      "name": "Taxi",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Taxi",
      "overview": "Louie De Palma is a cantankerous, acerbic taxi dispatcher in New York City. He tries to maintain order over a collection of varied and strange characters who drive for him. As he bullies and insults them from the safety of his “cage,” they form a special bond among themselves, becoming friends and supporting each other through the inevitable trials and tribulations of life.",
      "poster_path": "/pua3A2XSaO6bP6WhVDBRbcyRTAS.jpg",
      "vote_average": 7.3,
      "vote_count": 80,
      "popularity": 14.409
    },
    {
      "backdrop_path": "/5PD0MeGsoKFGZBn6BSc3VaMlEAN.jpg",
      "first_air_date": "1998-11-02",
      "genre_ids": [
        35
      ],
      "id": 4477,
      "name": "Becker",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Becker",
      "overview": "Becker is a dedicated, outspoken and talented doctor with a gruff exterior. While he tends to offend those who try to get close to him, he is extremely dedicated to his medical practice in the Bronx, N.Y., where he always goes the extra mile to help those in need. But Dr. John Becker looks at the world around him and sees a society gone mad, full of incongruities and just plain wrong thinking. And he has no qualms about saying anything that comes to mind – anything.",
      "poster_path": "/pgoZfQg5lF95WjlOR7ittxElJbq.jpg",
      "vote_average": 7.2,
      "vote_count": 59,
      "popularity": 10.58
    },
    {
      "backdrop_path": "/qdDYIMaLXzeglGSyoBIsCG6t1qs.jpg",
      "first_air_date": "1975-01-18",
      "genre_ids": [
        35
      ],
      "id": 1964,
      "name": "The Jeffersons",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "The Jeffersons",
      "overview": "Sitcom following a successful African-American couple, George and Louise “Weezyö Jefferson as they “move on up” from working-class Queens to a ritzy Manhattan apartment. A spin-off of All in the Family.",
      "poster_path": "/iFjIaWr9qDKMLnnh8tHwO6Tzp6D.jpg",
      "vote_average": 7.5,
      "vote_count": 43,
      "popularity": 15.607
    },
    {
      "original_name": "Don't Trust the B---- in Apartment 23",
      "id": 39331,
      "name": "Don't Trust the B---- in Apartment 23",
      "vote_count": 132,
      "vote_average": 6.9,
      "first_air_date": "2012-04-11",
      "poster_path": "/A0gKUbZ0VZKDoqyW6NvvZAE5F07.jpg",
      "genre_ids": [
        35
      ],
      "original_language": "en",
      "backdrop_path": "/iq7Ry2GRabD3otKeFEpiHzbCuOn.jpg",
      "overview": "After a naive Midwestern girl's big city dreams are dashed her first week in New York, she finds herself living with her worst nightmare in this hilarious, contemporary comedy about a female odd couple who are surrounded by an outrageous cast of characters.",
      "origin_country": [
        "US"
      ],
      "popularity": 13.68
    }
  ],
  "total_pages": 78,
  "total_results": 1541
}
"""

//https://api.themoviedb.org/3/tv/popular?api_key=5bd74c7a7bbfad5678fe1acd33fe732a&language=en-US&page=1
const val tvShowPage1 = """{
"page": 1,
"total_results": 10000,
"total_pages": 500,
"results": [
{
"original_name": "ドラゴンボールゼット",
"genre_ids": [
16,
35,
10759,
10765
],
"name": "Dragon Ball Z",
"popularity": 233.156,
"origin_country": [
"JP"
],
"vote_count": 1147,
"first_air_date": "1989-04-26",
"backdrop_path": "/ydf1CeiBLfdxiyNTpskM0802TKl.jpg",
"original_language": "ja",
"id": 12971,
"vote_average": 8.1,
"overview": "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
"poster_path": "/dBsDWUcdfbuZwglgyeeQ9ChRoS4.jpg"
},
{
"original_name": "Law & Order: Special Victims Unit",
"genre_ids": [
80,
18
],
"name": "Law & Order: Special Victims Unit",
"popularity": 223.045,
"origin_country": [
"US"
],
"vote_count": 1462,
"first_air_date": "1999-09-20",
"backdrop_path": "/cD9PxbrdWYgL7MUpD9eOYuiSe2P.jpg",
"original_language": "en",
"id": 2734,
"vote_average": 7.3,
"overview": "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
"poster_path": "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg"
},
{
"original_name": "Friends",
"genre_ids": [
35,
18
],
"name": "Friends",
"popularity": 203.47,
"origin_country": [
"US"
],
"vote_count": 2826,
"first_air_date": "1994-09-22",
"backdrop_path": "/l0qVZIpXtIo7km9u5Yqh0nKPOr5.jpg",
"original_language": "en",
"id": 1668,
"vote_average": 8.2,
"overview": "The misadventures of a group of friends as they navigate the pitfalls of work, life and love in Manhattan.",
"poster_path": "/f496cm9enuEsZkSPzCwnTESEK5s.jpg"
},
{
"original_name": "The Umbrella Academy",
"genre_ids": [
35,
18,
10759,
10765
],
"name": "The Umbrella Academy",
"popularity": 196.814,
"origin_country": [
"US"
],
"vote_count": 755,
"first_air_date": "2019-02-15",
"backdrop_path": "/qJxzjUjCpTPvDHldNnlbRC4OqEh.jpg",
"original_language": "en",
"id": 75006,
"vote_average": 8.3,
"overview": "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
"poster_path": "/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg"
},
{
"original_name": "Family Guy",
"genre_ids": [
16,
35
],
"name": "Family Guy",
"popularity": 196.086,
"origin_country": [
"US"
],
"vote_count": 2212,
"first_air_date": "1999-01-31",
"backdrop_path": "/4oE4vT4q0AD2cX3wcMBVzCsME8G.jpg",
"original_language": "en",
"id": 1434,
"vote_average": 6.7,
"overview": "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
"poster_path": "/q3E71oY6qgAEiw6YZIHDlHSLwer.jpg"
},
{
"original_name": "仮面ライダ",
"genre_ids": [
18,
10759,
10765
],
"name": "Kamen Rider",
"popularity": 195.413,
"origin_country": [
"JP"
],
"vote_count": 40,
"first_air_date": "1971-04-03",
"backdrop_path": null,
"original_language": "ja",
"id": 2661,
"vote_average": 5.3,
"overview": "A Japanese anthology series centered around a man who transforms into a bug-themed superhero.",
"poster_path": "/o57T19zgZakEpre3d9ddy1UZonp.jpg"
},
{
"original_name": "Supernatural",
"genre_ids": [
18,
9648,
10765
],
"name": "Supernatural",
"popularity": 193.607,
"origin_country": [
"US"
],
"vote_count": 3428,
"first_air_date": "2005-09-13",
"backdrop_path": "/nVRyd8hlg0ZLxBn9RaI7mUMQLnz.jpg",
"original_language": "en",
"id": 1622,
"vote_average": 7.9,
"overview": "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
"poster_path": "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg"
},
{
"original_name": "The Flash",
"genre_ids": [
18,
10765
],
"name": "The Flash",
"popularity": 185.47,
"origin_country": [
"US"
],
"vote_count": 5205,
"first_air_date": "2014-10-07",
"backdrop_path": "/jC1KqsFx8ZyqJyQa2Ohi7xgL7XC.jpg",
"original_language": "en",
"id": 60735,
"vote_average": 7.4,
"overview": "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
"poster_path": "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg"
},
{
"original_name": "NCIS",
"genre_ids": [
80,
18,
10759
],
"name": "NCIS",
"popularity": 180.73,
"origin_country": [
"US"
],
"vote_count": 1063,
"first_air_date": "2003-09-23",
"backdrop_path": "/4VuCtYBvZGq6Rk3gloigwlsTefE.jpg",
"original_language": "en",
"id": 4614,
"vote_average": 7,
"overview": "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
"poster_path": "/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg"
},
{
"original_name": "Grey's Anatomy",
"genre_ids": [
18
],
"name": "Grey's Anatomy",
"popularity": 180.047,
"origin_country": [
"US"
],
"vote_count": 3059,
"first_air_date": "2005-03-27",
"backdrop_path": "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
"original_language": "en",
"id": 1416,
"vote_average": 7.8,
"overview": "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
"poster_path": "/jnsvc7gCKocXnrTXF6p03cICTWb.jpg"
},
{
"original_name": "The Simpsons",
"genre_ids": [
16,
35
],
"name": "The Simpsons",
"popularity": 177.401,
"origin_country": [
"US"
],
"vote_count": 4177,
"first_air_date": "1989-12-16",
"backdrop_path": "/adZ9ldSlkGfLfsHNbh37ZThCcgU.jpg",
"original_language": "en",
"id": 456,
"vote_average": 7.6,
"overview": "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
"poster_path": "/qcr9bBY6MVeLzriKCmJOv1562uY.jpg"
},
{
"original_name": "ポケモン",
"id": 60572,
"name": "Pokémon",
"popularity": 175.987,
"vote_count": 367,
"vote_average": 6.8,
"first_air_date": "1997-04-01",
"poster_path": "/rOuGm07PxBhEsK9TaGPRQVJQm1X.jpg",
"genre_ids": [
16,
10759,
10765,
10762
],
"original_language": "ja",
"backdrop_path": "/tvjCdVRkaaab2ezM9BctkAOXeyW.jpg",
"overview": "Join Ash Ketchum, accompanied by his partner Pikachu, as he travels through many regions, meets new friends and faces new challenges on his quest to become a Pokémon Master.",
"origin_country": [
"JP"
]
},
{
"original_name": "ナルト",
"genre_ids": [
16,
35,
18,
10759
],
"name": "Naruto",
"popularity": 163.532,
"origin_country": [
"JP"
],
"vote_count": 1496,
"first_air_date": "2002-10-03",
"backdrop_path": "/6tWJaE0XEEkB7lpxDE7F3Mmhtt6.jpg",
"original_language": "ja",
"id": 46260,
"vote_average": 8.4,
"overview": "In another world, ninja are the ultimate power, and in the Village Hidden in the Leaves live the stealthiest ninja in the land. Twelve years earlier, the fearsome Nine-Tailed Fox terrorized the village and claimed many lives before it was subdued and its spirit sealed within the body of a baby boy. That boy, Naruto Uzumaki, has grown up to become a ninja-in-training who's more interested in pranks than in studying ninjutsu.. but Naruto is determined to become the greatest ninja ever!",
"poster_path": "/vauCEnR7CiyBDzRCeElKkCaXIYu.jpg"
},
{
"original_name": "Game of Thrones",
"genre_ids": [
18,
10765
],
"name": "Game of Thrones",
"popularity": 161.82,
"origin_country": [
"US"
],
"vote_count": 9495,
"first_air_date": "2011-04-17",
"backdrop_path": "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
"original_language": "en",
"id": 1399,
"vote_average": 8.3,
"overview": "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
"poster_path": "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg"
},
{
"original_name": "Criminal Minds",
"genre_ids": [
80,
18,
9648
],
"name": "Criminal Minds",
"popularity": 156.965,
"origin_country": [
"US"
],
"vote_count": 1377,
"first_air_date": "2005-09-22",
"backdrop_path": "/nWw20ipGipwdOyLTkFfX2Uz4Grp.jpg",
"original_language": "en",
"id": 4057,
"vote_average": 7.9,
"overview": "An elite team of FBI profilers analyze the country's most twisted criminal minds, anticipating their next moves before they strike again. The Behavioral Analysis Unit's most experienced agent is David Rossi, a founding member of the BAU who returns to help the team solve new cases.",
"poster_path": "/7TCwgX7oQKxcWYEhSPRmaHe6ULN.jpg"
},
{
"original_name": "Rick and Morty",
"genre_ids": [
16,
35,
10759,
10765
],
"name": "Rick and Morty",
"popularity": 156.773,
"origin_country": [
"US"
],
"vote_count": 3024,
"first_air_date": "2013-12-02",
"backdrop_path": "/eV3XnUul4UfIivz3kxgeIozeo50.jpg",
"original_language": "en",
"id": 60625,
"vote_average": 8.7,
"overview": "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
"poster_path": "/8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg"
},
{
"original_name": "Marvel's Agents of S.H.I.E.L.D.",
"genre_ids": [
18,
10759,
10765
],
"name": "Marvel's Agents of S.H.I.E.L.D.",
"popularity": 155.273,
"origin_country": [
"US"
],
"vote_count": 1914,
"first_air_date": "2013-09-24",
"backdrop_path": "/mUCV0W6TaAk8UWA5yAmqCywC63F.jpg",
"original_language": "en",
"id": 1403,
"vote_average": 7.2,
"overview": "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
"poster_path": "/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg"
},
{
"original_name": "American Dad!",
"genre_ids": [
16,
35
],
"name": "American Dad!",
"popularity": 155.114,
"origin_country": [
"US"
],
"vote_count": 981,
"first_air_date": "2005-02-06",
"backdrop_path": "/2OeS0YPFpqCmi7SA5W6aOPIChc3.jpg",
"original_language": "en",
"id": 1433,
"vote_average": 6.4,
"overview": "The series focuses on an eccentric motley crew that is the Smith family and their three housemates: Father, husband, and breadwinner Stan Smith; his better half housewife, Francine Smith; their college-aged daughter, Hayley Smith; and their high-school-aged son, Steve Smith. Outside of the Smith family, there are three additional main characters, including Hayley's boyfriend turned husband, Jeff Fischer; the family's man-in-a-goldfish-body pet, Klaus; and most notably the family's zany alien, Roger, who is \"full of masquerades, brazenness, and shocking antics.\"",
"poster_path": "/1aklTBd36LFiFNroOSiherLvLdu.jpg"
},
{
"original_name": "Miraculous, les aventures de Ladybug et Chat Noir",
"genre_ids": [
16,
10751,
10759,
10762
],
"name": "Miraculous: Tales of Ladybug & Cat Noir",
"popularity": 152.498,
"origin_country": [
"FR"
],
"vote_count": 602,
"first_air_date": "2015-10-19",
"backdrop_path": "/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
"original_language": "fr",
"id": 65334,
"vote_average": 7.7,
"overview": "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
"poster_path": "/nu6dcBfxr4VmOBj4k1S9r0r1MOW.jpg"
},
{
"original_name": "Law & Order",
"genre_ids": [
80,
18,
9648
],
"name": "Law & Order",
"popularity": 147.4,
"origin_country": [
"US"
],
"vote_count": 254,
"first_air_date": "1990-09-13",
"backdrop_path": "/A4kt2TAsV8bq0csfwJbfgiZtG4g.jpg",
"original_language": "en",
"id": 549,
"vote_average": 7.5,
"overview": "Follows a crime (usually a murder), usually adapted from current headlines, from two separate vantage points, the police investigation and the prosecution in court.",
"poster_path": "/1J8QWZM7DQxJ2b7vYK3munMK5rh.jpg"
}
]
}"""

//https://api.themoviedb.org/3/tv/popular?api_key=5bd74c7a7bbfad5678fe1acd33fe732a&language=en-US&page=2
const val tvShowPage2 = """{
"page": 2,
"total_results": 10000,
"total_pages": 500,
"results": [
{
"original_name": "Doctor Who",
"genre_ids": [
18,
10759,
10765
],
"name": "Doctor Who",
"popularity": 147.32,
"origin_country": [
"GB"
],
"vote_count": 1728,
"first_air_date": "2005-03-26",
"backdrop_path": "/nfH8SZJVOxcBlFaqqtoqS5hHizG.jpg",
"original_language": "en",
"id": 57243,
"vote_average": 7,
"overview": "The Doctor is a Time Lord: a 900 year old alien with 2 hearts, part of a gifted civilization who mastered time travel. The Doctor saves planets for a living—more of a hobby actually, and the Doctor's very, very good at it.",
"poster_path": "/cDDb7WA2i7cENhkEEjXEDrXGyNL.jpg"
},
{
"original_name": "Arrow",
"genre_ids": [
80,
18,
9648,
10759
],
"name": "Arrow",
"popularity": 144.718,
"origin_country": [
"US"
],
"vote_count": 3748,
"first_air_date": "2012-10-10",
"backdrop_path": "/fFT7T9y9NVRdcdVh81roVrJBcDh.jpg",
"original_language": "en",
"id": 1412,
"vote_average": 6.3,
"overview": "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
"poster_path": "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
},
{
"original_name": "Lucifer",
"genre_ids": [
80,
10765
],
"name": "Lucifer",
"popularity": 144.696,
"origin_country": [
"US"
],
"vote_count": 3172,
"first_air_date": "2016-01-25",
"backdrop_path": "/vauFEN12Exuck3nO9WWmjB1R5cJ.jpg",
"original_language": "en",
"id": 63174,
"vote_average": 8.3,
"overview": "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
"poster_path": "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
},
{
"original_name": "ブリーチ",
"genre_ids": [
16,
10759,
10765
],
"name": "Bleach",
"popularity": 142.933,
"origin_country": [
"JP"
],
"vote_count": 276,
"first_air_date": "2004-10-05",
"backdrop_path": "/qtfMr08KQsWXnCHY0a96N8NpQ2l.jpg",
"original_language": "ja",
"id": 30984,
"vote_average": 8,
"overview": "For as long as he can remember, Ichigo Kurosaki has been able to see ghosts. But when he meets Rukia, a Soul Reaper who battles evil spirits known as Hollows, he finds his life is changed forever. Now, with a newfound wealth of spiritual energy, Ichigo discovers his true calling: to protect the living and the dead from evil.",
"poster_path": "/oehFopDepz3tgxTzISOb97C41jy.jpg"
},
{
"original_name": "Prison Break",
"genre_ids": [
80,
18,
10759
],
"name": "Prison Break",
"popularity": 141.234,
"origin_country": [
"US"
],
"vote_count": 2196,
"first_air_date": "2005-08-29",
"backdrop_path": "/92OPBZpLc82y8CzOtrQ8P9RDvIN.jpg",
"original_language": "en",
"id": 2288,
"vote_average": 7.9,
"overview": "Due to a political conspiracy, an innocent man is sent to death row and his only hope is his brother, who makes it his mission to deliberately get himself sent to the same prison in order to break the both of them out, from the inside out.",
"poster_path": "/j7HFOunuAVpOdiBhXpjwRxIgK5y.jpg"
},
{
"original_name": "Riverdale",
"genre_ids": [
18,
9648
],
"name": "Riverdale",
"popularity": 140.989,
"origin_country": [
"US"
],
"vote_count": 4241,
"first_air_date": "2017-01-26",
"backdrop_path": "/9hvhGtcsGhQY58pukw8w55UEUbL.jpg",
"original_language": "en",
"id": 69050,
"vote_average": 8.6,
"overview": "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
"poster_path": "/4X7o1ssOEvp4BFLim1AZmPNcYbU.jpg"
},
{
"original_name": "The Smurfs",
"genre_ids": [
16,
10751,
10762,
10765
],
"name": "The Smurfs",
"popularity": 140.627,
"origin_country": [
"BE",
"US"
],
"vote_count": 161,
"first_air_date": "1981-09-12",
"backdrop_path": "/7HYHXRfHreJD4afBed08nLsXPVN.jpg",
"original_language": "en",
"id": 5687,
"vote_average": 6.5,
"overview": "Classic Saturday-morning cartoon series featuring magical blue elf-like creatures called Smurfs. The Smurfs, named for their personalities, inhabit a village of mushroom houses in an enchanted forest. These loveable creatures are led by Papa Smurf and live carefree... except for one major threat to their existance: Gargamel, an evil but inept wizard who lives in a stone-built house in the forest; and his feline companion, the equally nasty Azrael. ",
"poster_path": "/ozlfM7st1MlcfNRUGHEOoDEccNr.jpg"
},
{
"original_name": "Breaking Bad",
"genre_ids": [
18
],
"name": "Breaking Bad",
"popularity": 137.27,
"origin_country": [
"US"
],
"vote_count": 5192,
"first_air_date": "2008-01-20",
"backdrop_path": "/tsRy63Mu5cu8etL1X7ZLyf7UP1M.jpg",
"original_language": "en",
"id": 1396,
"vote_average": 8.6,
"overview": "When Walter White, a New Mexico chemistry teacher, is diagnosed with Stage III cancer and given a prognosis of only two years left to live. He becomes filled with a sense of fearlessness and an unrelenting desire to secure his family's financial future at any cost as he enters the dangerous world of drugs and crime.",
"poster_path": "/ggFHVNu6YYI5L9pCfOacjizRGt.jpg"
},
{
"original_name": "Two and a Half Men",
"genre_ids": [
35
],
"name": "Two and a Half Men",
"popularity": 135.453,
"origin_country": [
"US"
],
"vote_count": 1274,
"first_air_date": "2003-09-21",
"backdrop_path": "/nMJO71S7r7bn1ABvARtfuUQJbcS.jpg",
"original_language": "en",
"id": 2691,
"vote_average": 6.9,
"overview": "A hedonistic jingle writer's free-wheeling life comes to an abrupt halt when his brother and 10-year-old nephew move into his beach-front house.",
"poster_path": "/A9QDK4OWpv41W27kCv0LXe30k9S.jpg"
},
{
"original_name": "South Park",
"genre_ids": [
16,
35
],
"name": "South Park",
"popularity": 135.181,
"origin_country": [
"US"
],
"vote_count": 1679,
"first_air_date": "1997-08-13",
"backdrop_path": "/gLf7hpvlDgqLDp6IUhTQTMivu6h.jpg",
"original_language": "en",
"id": 2190,
"vote_average": 8,
"overview": "Follows the misadventures of four irreverent grade-schoolers in the quiet, dysfunctional town of South Park, Colorado.",
"poster_path": "/9BvRze9keEyTzB6ewmDvjzFGYuG.jpg"
},
{
"original_name": "Vikings",
"genre_ids": [
18,
10759
],
"name": "Vikings",
"popularity": 134.188,
"origin_country": [
"CA"
],
"vote_count": 2891,
"first_air_date": "2013-03-03",
"backdrop_path": "/aq2yEMgRQBPfRkrO0Repo2qhUAT.jpg",
"original_language": "en",
"id": 44217,
"vote_average": 7.8,
"overview": "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
"poster_path": "/ff1zhqvwfS5HvRNcA5UFrH0PA2q.jpg"
},
{
"original_name": "戦国お伽草子–犬夜叉",
"genre_ids": [
16,
35,
18,
10759,
10765
],
"name": "InuYasha",
"popularity": 133.642,
"origin_country": [
"JP"
],
"vote_count": 464,
"first_air_date": "2000-10-16",
"backdrop_path": "/r5xkqlEBhzfffQjxHRMuXBrwKiv.jpg",
"original_language": "ja",
"id": 35610,
"vote_average": 8.5,
"overview": "Kagome Higurashi is a modern day young girl who lives with her family by the old Higure shrine. Unbeknownst to Kagome, she is the reincarnation of priestess Kikyo and posseses the \"Jewel of Four Souls\" (the Shikon jewel). One ill-fated day, Kagome locates an ancient well near her home and is abruptly transported through the well and into a feudal Japan, inhabited by demons. There, she encounters Inuyasha, son of a powerful demon father and a human mother, who is pinned to a tree by an enchanted arrow.",
"poster_path": "/zEHGWEMtqdXXhao8PeRqTmpaE6P.jpg"
},
{
"original_name": "CSI: Crime Scene Investigation",
"genre_ids": [
80,
18,
9648
],
"name": "CSI: Crime Scene Investigation",
"popularity": 131.876,
"origin_country": [
"US"
],
"vote_count": 441,
"first_air_date": "2000-10-06",
"backdrop_path": "/6MhOZyqG7fyAxuiYsyJ4WHO9Gk6.jpg",
"original_language": "en",
"id": 1431,
"vote_average": 7.2,
"overview": "A Las Vegas team of forensic investigators are trained to solve criminal cases by scouring the crime scene, collecting irrefutable evidence and finding the missing pieces that solve the mystery.",
"poster_path": "/9XecVOLLkbGBNnpF2PvP8vQBmww.jpg"
},
{
"original_name": "The 100",
"genre_ids": [
18,
10759,
10765
],
"name": "The 100",
"popularity": 129.324,
"origin_country": [
"US"
],
"vote_count": 3695,
"first_air_date": "2014-03-19",
"backdrop_path": "/hTExot1sfn7dHZjGrk0Aiwpntxt.jpg",
"original_language": "en",
"id": 48866,
"vote_average": 7.6,
"overview": "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
"poster_path": "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg"
},
{
"original_name": "Baywatch",
"genre_ids": [
18,
10759
],
"name": "Baywatch",
"popularity": 126.195,
"origin_country": [
"US"
],
"vote_count": 201,
"first_air_date": "1989-04-23",
"backdrop_path": "/o2F0cIIuEOuai7Vzv0Rh7YvRppT.jpg",
"original_language": "en",
"id": 4386,
"vote_average": 5.5,
"overview": "The thrilling adventures of the iconic Los Angeles County Lifeguards as they patrol the beautiful beaches of Southern California.",
"poster_path": "/AiXn0RyRbaRkmFweSa9n3QXTF14.jpg"
},
{
"original_name": "The Blacklist",
"genre_ids": [
80,
18,
9648
],
"name": "The Blacklist",
"popularity": 125.437,
"origin_country": [
"US"
],
"vote_count": 1548,
"first_air_date": "2013-09-23",
"backdrop_path": "/8b4X7cFOagllHuERcefvDpECwDz.jpg",
"original_language": "en",
"id": 46952,
"vote_average": 7.2,
"overview": "Raymond \"Red\" Reddington, one of the FBI's most wanted fugitives, surrenders in person at FBI Headquarters in Washington, D.C. He claims that he and the FBI have the same interests: bringing down dangerous criminals and terrorists. In the last two decades, he's made a list of criminals and terrorists that matter the most but the FBI cannot find because it does not know they exist. Reddington calls this \"The Blacklist\". Reddington will co-operate, but insists that he will speak only to Elizabeth Keen, a rookie FBI profiler.",
"poster_path": "/bgbQCW4fE9b6wSOSC6Fb4FfVzsW.jpg"
},
{
"original_name": "Sesame Street",
"genre_ids": [
35,
10762
],
"name": "Sesame Street",
"popularity": 123.776,
"origin_country": [
"US"
],
"vote_count": 127,
"first_air_date": "1969-11-10",
"backdrop_path": "/rYlCELMEo1IFfjJB6EP1ket8gM.jpg",
"original_language": "en",
"id": 502,
"vote_average": 7.1,
"overview": "On a special inner city street, the inhabitants—human and muppet—teach preschoolers basic educational and social concepts using comedy, cartoons, games, and songs.",
"poster_path": "/xNWfDneXNj4EJjqFR1gFbM3wbnc.jpg"
},
{
"original_name": "Smallville",
"genre_ids": [
18,
9648,
10759,
10765
],
"name": "Smallville",
"popularity": 123.16,
"origin_country": [
"US"
],
"vote_count": 1294,
"first_air_date": "2001-10-16",
"backdrop_path": "/qfiB0VDNEXP9IZDrOhNakwPZ0Tc.jpg",
"original_language": "en",
"id": 4604,
"vote_average": 7.9,
"overview": "The origins of the world’s greatest hero–from Krypton refugee Kal-el’s arrival on Earth through his tumultuous teen years to Clark Kent’s final steps toward embracing his destiny as the Man of Steel.",
"poster_path": "/dc9a9n4g9Ibs485t5eBtyNG0mcr.jpg"
},
{
"original_name": "Monk",
"genre_ids": [
35,
80,
18,
9648
],
"name": "Monk",
"popularity": 122.421,
"origin_country": [
"US"
],
"vote_count": 395,
"first_air_date": "2002-07-12",
"backdrop_path": "/sqtSI7uHhFSCvTx60pdqPXGhYCF.jpg",
"original_language": "en",
"id": 1695,
"vote_average": 7.8,
"overview": "Adrian Monk was once a rising star with the San Francisco Police Department, legendary for using unconventional means to solve the department's most baffling cases. But after the tragic (and still unsolved) murder of his wife Trudy, he developed an extreme case of obsessive-compulsive disorder. Now working as a private consultant, Monk continues to investigate cases in the most unconventional ways.",
"poster_path": "/bjAAXWkivX5MPJYg4dBmk3R7nse.jpg"
},
{
"original_name": "Bones",
"genre_ids": [
80,
18
],
"name": "Bones",
"popularity": 122.382,
"origin_country": [
"US"
],
"vote_count": 1129,
"first_air_date": "2005-09-13",
"backdrop_path": "/e9n87p3Ax67spq3eUgLB6rjIEow.jpg",
"original_language": "en",
"id": 1911,
"vote_average": 8,
"overview": "Dr. Temperance Brennan and her colleagues at the Jeffersonian's Medico-Legal Lab assist Special Agent Seeley Booth with murder investigations when the remains are so badly decomposed, burned or destroyed that the standard identification methods are useless.",
"poster_path": "/1bwF1daWnsEYYjbHXiEMdS587fR.jpg"
}
]
}
"""

const val tvShowInvalidPage = """{
"page": 2,
"total_results": 10000,
"total_pages": 500,
"results": []
}
"""

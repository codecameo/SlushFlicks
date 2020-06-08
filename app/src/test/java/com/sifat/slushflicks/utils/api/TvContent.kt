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

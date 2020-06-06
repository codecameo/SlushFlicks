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

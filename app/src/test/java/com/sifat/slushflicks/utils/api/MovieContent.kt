package com.sifat.slushflicks.utils.api

const val movieCastResponse = """
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

const val movieVideoResponse = """
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

const val movieNoVideoResponse = """
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

const val movieSimilarResponse = """
{
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/vay7LlUASz0W25ZEwDMWaOL299N.jpg",
      "genre_ids": [
        12,
        28,
        53,
        878
      ],
      "id": 605,
      "original_language": "en",
      "original_title": "The Matrix Revolutions",
      "overview": "The human city of Zion defends itself against the massive invasion of the machines as Neo fights to end the war at another front while also opposing the rogue Agent Smith.",
      "poster_path": "/cm14gG8xBghwIAy1GX0ryI2HA4U.jpg",
      "release_date": "2003-11-03",
      "title": "The Matrix Revolutions",
      "video": false,
      "vote_average": 6.6,
      "vote_count": 6190,
      "popularity": 20.849
    },
    {
      "id": 624860,
      "video": false,
      "vote_count": 0,
      "vote_average": 0,
      "title": "The Matrix 4",
      "release_date": "2021-05-19",
      "original_language": "en",
      "original_title": "The Matrix 4",
      "genre_ids": [
        28,
        878
      ],
      "backdrop_path": null,
      "adult": false,
      "overview": "The fourth installment in The Matrix franchise. Plot unknown.",
      "poster_path": "/uoQfAYR1NTax9akJ1ceeQTKNXeo.jpg",
      "popularity": 7.59
    },
    {
      "id": 604,
      "video": false,
      "vote_count": 6886,
      "vote_average": 6.9,
      "title": "The Matrix Reloaded",
      "release_date": "2003-05-15",
      "original_language": "en",
      "original_title": "The Matrix Reloaded",
      "genre_ids": [
        28,
        12,
        878,
        53
      ],
      "backdrop_path": "/sDxCd4nt3eR4qOCW1GoD0RabQtq.jpg",
      "adult": false,
      "overview": "Six months after the events depicted in The Matrix, Neo has proved to be a good omen for the free humans, as more and more humans are being freed from the matrix and brought to Zion, the one and only stronghold of the Resistance.  Neo himself has discovered his superpowers including super speed, ability to see the codes of the things inside the matrix and a certain degree of pre-cognition. But a nasty piece of news hits the human resistance: 250,000 machine sentinels are digging to Zion and would reach them in 72 hours. As Zion prepares for the ultimate war, Neo, Morpheus and Trinity are advised by the Oracle to find the Keymaker who would help them reach the Source.  Meanwhile Neo's recurrent dreams depicting Trinity's death have got him worried and as if it was not enough, Agent Smith has somehow escaped deletion, has become more powerful than before and has fixed Neo as his next target.",
      "poster_path": "/aA5qHS0FbSXO8PxcxUIHbDrJyuh.jpg",
      "popularity": 19.046
    },
    {
      "id": 8202,
      "video": false,
      "vote_count": 1344,
      "vote_average": 5.5,
      "title": "Æon Flux",
      "release_date": "2005-11-30",
      "original_language": "en",
      "original_title": "Æon Flux",
      "genre_ids": [
        28,
        878
      ],
      "backdrop_path": "/18TnrNODUgYaV3URw6VB4v6KRyo.jpg",
      "adult": false,
      "overview": "400 years into the future, disease has wiped out the majority of the world's population, except one walled city, Bregna, ruled by a congress of scientists. When Æon Flux, the top operative in the underground 'Monican' rebellion, is sent on a mission to kill a government leader, she uncovers a world of secrets.",
      "poster_path": "/t3ywK0oifrCc07fu0RGXWDxb1DZ.jpg",
      "popularity": 14.768
    },
    {
      "id": 281,
      "video": false,
      "vote_count": 723,
      "vote_average": 7,
      "title": "Strange Days",
      "release_date": "1995-10-13",
      "original_language": "en",
      "original_title": "Strange Days",
      "genre_ids": [
        80,
        18,
        878,
        53
      ],
      "backdrop_path": "/kAl96eiG6Z83sHTfSqrHyBKtl3g.jpg",
      "adult": false,
      "overview": "Set in the year 1999 during the last days of the old millennium, the movie tells the story of Lenny Nero, an ex-cop who now deals with data-discs containing recorded memories and emotions. One day he receives a disc which contains the memories of a murderer killing a prostitute. Lenny investigates and is pulled deeper and deeper in a whirl of blackmail, murder and rape. Will he survive and solve the case?",
      "poster_path": "/6cquF8GbEtNUlfz5yjbCoYzM9Pq.jpg",
      "popularity": 12.472
    },
    {
      "id": 315837,
      "video": false,
      "vote_count": 5885,
      "vote_average": 6,
      "title": "Ghost in the Shell",
      "release_date": "2017-03-29",
      "original_language": "en",
      "original_title": "Ghost in the Shell",
      "genre_ids": [
        28,
        18,
        878,
        53
      ],
      "backdrop_path": "/sbRXCEsh35hIiHhc2XQRDZ7hLzH.jpg",
      "adult": false,
      "overview": "In the near future, Major (Scarlett Johansson) is the first of her kind: A human saved from a terrible crash, who is cyber-enhanced to be a perfect soldier devoted to stopping the world's most dangerous criminals. When terrorism reaches a new level that includes the ability to hack into people's minds and control them, Major is uniquely qualified to stop it. As she prepares to face a new enemy, Major discovers that she has been lied to: her life was not saved, it was stolen. She will stop at nothing to recover her past, find out who did this to her and stop them before they do it to others. Based on the internationally acclaimed Japanese Manga, \"The Ghost in the Shell.\"",
      "poster_path": "/myRzRzCxdfUWjkJWgpHHZ1oGkJd.jpg",
      "popularity": 19.049
    },
    {
      "id": 335984,
      "video": false,
      "vote_count": 8332,
      "vote_average": 7.4,
      "title": "Blade Runner 2049",
      "release_date": "2017-10-04",
      "original_language": "en",
      "original_title": "Blade Runner 2049",
      "genre_ids": [
        18,
        878
      ],
      "backdrop_path": "/sAtoMqDVhNDQBc3QJL3RF6hlhGq.jpg",
      "adult": false,
      "overview": "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos. K's discovery leads him on a quest to find Rick Deckard, a former LAPD blade runner who has been missing for 30 years.",
      "poster_path": "/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
      "popularity": 47.39
    },
    {
      "id": 1090,
      "video": false,
      "vote_count": 760,
      "vote_average": 7,
      "title": "The Thirteenth Floor",
      "release_date": "1999-04-16",
      "original_language": "en",
      "original_title": "The Thirteenth Floor",
      "genre_ids": [
        9648,
        878,
        53
      ],
      "backdrop_path": "/y6KD0zMBfLw6rEzHRKO550dDyvM.jpg",
      "adult": false,
      "overview": "Los Angeles. A wealthy man, known as Mr. Fuller, discovers a shocking secret about the world he lives in. Fearing for his life, he leaves a desperate message for a friend of his in the most unexpected place.",
      "poster_path": "/7oaie3ZBc9UuWZLF24crro1pone.jpg",
      "popularity": 10.367
    },
    {
      "adult": false,
      "backdrop_path": "/jGM7EEYZ5A2cSGbplKX3l5iQs2V.jpg",
      "genre_ids": [
        28,
        12,
        16,
        35,
        10751
      ],
      "id": 140300,
      "original_language": "en",
      "original_title": "Kung Fu Panda 3",
      "overview": "Continuing his \"legendary adventures of awesomeness\", Po must face two hugely epic, but different threats: one supernatural and the other a little closer to his home.",
      "poster_path": "/nlr2oxuYsHXt0wdtmzaOuVBoNC0.jpg",
      "release_date": "2016-01-23",
      "title": "Kung Fu Panda 3",
      "video": false,
      "vote_average": 6.7,
      "vote_count": 3521,
      "popularity": 17.86
    },
    {
      "adult": false,
      "backdrop_path": "/wjFsB6FiHjQP8yNIdkiyR6KtuAQ.jpg",
      "genre_ids": [
        80,
        28,
        878
      ],
      "id": 9739,
      "original_language": "en",
      "original_title": "Demolition Man",
      "overview": "Simon Phoenix, a violent criminal cryogenically frozen in 1996, escapes during a parole hearing in 2032 in the utopia of San Angeles. Police are incapable of dealing with his violent ways and turn to his captor, who had also been cryogenically frozen after being wrongfully accused of killing 30 innocent people while apprehending Phoenix.",
      "poster_path": "/49GjSZn9FnSnrRFmsA4x5WAj8oB.jpg",
      "release_date": "1993-10-08",
      "title": "Demolition Man",
      "video": false,
      "vote_average": 6.5,
      "vote_count": 2107,
      "popularity": 14.89
    },
    {
      "id": 78,
      "video": false,
      "vote_count": 9166,
      "vote_average": 7.9,
      "title": "Blade Runner",
      "release_date": "1982-06-25",
      "original_language": "en",
      "original_title": "Blade Runner",
      "genre_ids": [
        18,
        878,
        53
      ],
      "backdrop_path": "/eIi3klFf7mp3oL5EEF4mLIDs26r.jpg",
      "adult": false,
      "overview": "In the smog-choked dystopian Los Angeles of 2019, blade runner Rick Deckard is called out of retirement to terminate a quartet of replicants who have escaped to Earth seeking their creator for a way to extend their short life spans.",
      "poster_path": "/vfzE3pjE5G7G7kcZWrA3fnbZo7V.jpg",
      "popularity": 32.771
    },
    {
      "id": 399579,
      "video": false,
      "vote_count": 5101,
      "vote_average": 7,
      "title": "Alita: Battle Angel",
      "release_date": "2019-01-31",
      "original_language": "en",
      "original_title": "Alita: Battle Angel",
      "genre_ids": [
        28,
        12,
        878
      ],
      "backdrop_path": "/8RKBHHRqOMOLh5qW3sS6TSFTd8h.jpg",
      "adult": false,
      "overview": "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
      "poster_path": "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
      "popularity": 33.492
    },
    {
      "adult": false,
      "backdrop_path": "/m1LUdsYnFThcrbvRzlpbsFioEQT.jpg",
      "genre_ids": [
        28,
        16,
        878
      ],
      "id": 9323,
      "original_language": "ja",
      "original_title": "GHOST IN THE SHELL",
      "overview": "In the year 2029, the barriers of our world have been broken down by the net and by cybernetics, but this brings new vulnerability to humans in the form of brain-hacking. When a highly-wanted hacker known as 'The Puppetmaster' begins involving them in politics, Section 9, a group of cybernetically enhanced cops, are called in to investigate and stop the Puppetmaster.",
      "poster_path": "/9gC88zYUBARRSThcG93MvW14sqx.jpg",
      "release_date": "1995-11-18",
      "title": "Ghost in the Shell",
      "video": false,
      "vote_average": 7.9,
      "vote_count": 1793,
      "popularity": 16.09
    },
    {
      "id": 500664,
      "video": false,
      "vote_count": 2090,
      "vote_average": 7.4,
      "title": "Upgrade",
      "release_date": "2018-06-01",
      "original_language": "en",
      "original_title": "Upgrade",
      "genre_ids": [
        28,
        27,
        878,
        53
      ],
      "backdrop_path": "/qUAGip3XiaVN668WXF2iyukWgUu.jpg",
      "adult": false,
      "overview": "A brutal mugging leaves Grey Trace paralyzed in the hospital and his beloved wife dead. A billionaire inventor soon offers Trace a cure — an artificial intelligence implant called STEM that will enhance his body. Now able to walk, Grey finds that he also has superhuman strength and agility — skills he uses to seek revenge against the thugs who destroyed his life.",
      "poster_path": "/8fDtXi6gVw8WUMWGT9XFz7YwkuE.jpg",
      "popularity": 31.47
    },
    {
      "adult": false,
      "backdrop_path": "/wa5eEBwjI01yhNMKPwIaqeBiw2d.jpg",
      "genre_ids": [
        12,
        28,
        53
      ],
      "id": 9053,
      "original_language": "en",
      "original_title": "DOA: Dead or Alive",
      "overview": "Four beautiful rivals at an invitation-only martial-arts tournament join forces against a sinister threat. Princess Kasumi is an aristocratic warrior trained by martial-arts masters. Tina Armstrong is a wrestling superstar. Helena Douglas is an athlete with a tragic past. Christie Allen earns her keep as a thief and an assassin-for-hire.",
      "poster_path": "/vWVm99ldbCvdjfIX8F4mqzcooEV.jpg",
      "release_date": "2006-09-07",
      "title": "DOA: Dead or Alive",
      "video": false,
      "vote_average": 4.9,
      "vote_count": 455,
      "popularity": 12.39
    },
    {
      "id": 21733,
      "video": false,
      "vote_count": 96,
      "vote_average": 7,
      "title": "Dragons Forever",
      "release_date": "1988-02-11",
      "original_language": "cn",
      "original_title": "飛龍猛將",
      "genre_ids": [
        28,
        35,
        10749
      ],
      "backdrop_path": "/5KwIMLRVOfwVmYId7IEkhF5vssp.jpg",
      "adult": false,
      "overview": "Jackie Chan stars as a hot-shot lawyer hired by a Hong Kong chemical plant to dispose of opposition to their polluting ways. But when he falls for a beautiful woman out to stop the plant, Jackie is torn in a conflict of interest and asks his trusty friends Samo and Biao to help out at least until they discover the true purpose of the plant.",
      "poster_path": "/wKwHhFRjJFjlBF9R6JAqfkAuJAJ.jpg",
      "popularity": 9.337
    },
    {
      "adult": false,
      "backdrop_path": "/5BxrMNGl3YDiWgHCVJu8iLQoJDM.jpg",
      "genre_ids": [
        878
      ],
      "id": 445651,
      "original_language": "en",
      "original_title": "The Darkest Minds",
      "overview": "After a disease kills 98% of America's children, the surviving 2% develop superpowers and are placed in internment camps. A 16-year-old girl escapes her camp and joins a group of other teens on the run from the government.",
      "poster_path": "/94RaS52zmsqaiAe1TG20pdbJCZr.jpg",
      "release_date": "2018-07-25",
      "title": "The Darkest Minds",
      "video": false,
      "vote_average": 6.9,
      "vote_count": 1663,
      "popularity": 18.626
    },
    {
      "adult": false,
      "backdrop_path": "/a206ULr44k6Q2xS7jHFhF3u5FqP.jpg",
      "genre_ids": [
        53,
        878,
        28,
        12
      ],
      "id": 307663,
      "original_language": "en",
      "original_title": "Vice",
      "overview": "Julian Michaels has designed the ultimate resort: VICE, where anything goes and the customers can play out their wildest fantasies with artificial inhabitants who look, think and feel like humans. When an artificial becomes self-aware and escapes, she finds herself caught in the crossfire between Julian's mercenaries and a cop who is hell-bent on shutting down Vice, and stopping the violence once and for all.",
      "poster_path": "/nPqNoQ55RIrmmo9mpqg1jE3ENza.jpg",
      "release_date": "2015-01-16",
      "title": "Vice",
      "video": false,
      "vote_average": 4.3,
      "vote_count": 366,
      "popularity": 15.876
    },
    {
      "id": 2048,
      "video": false,
      "vote_count": 8204,
      "vote_average": 6.9,
      "title": "I, Robot",
      "release_date": "2004-07-15",
      "original_language": "en",
      "original_title": "I, Robot",
      "genre_ids": [
        28,
        878
      ],
      "backdrop_path": "/lOXVbF2mBK0xUt8sLInF3TIk5YS.jpg",
      "adult": false,
      "overview": "In 2035, where robots are common-place and abide by the three laws of robotics, a techno-phobic cop investigates an apparent suicide. Suspecting that a robot may be responsible for the death, his investigation leads him to believe that humanity may be in danger.",
      "poster_path": "/zDu0TsCZHPzDO0d0iG9QynKGr4J.jpg",
      "popularity": 30.71
    },
    {
      "id": 10045,
      "video": false,
      "vote_count": 1120,
      "vote_average": 6.6,
      "title": "District B13",
      "release_date": "2004-11-09",
      "original_language": "fr",
      "original_title": "Banlieue 13",
      "genre_ids": [
        28,
        878,
        53
      ],
      "backdrop_path": "/c0XgpQURus7xprAhAwtkkyf3iwd.jpg",
      "adult": false,
      "overview": "Set in the ghettos of Paris in 2010, an undercover cop and ex-thug try to infiltrate a gang in order to defuse a neutron bomb.",
      "poster_path": "/u9gOfYelDwmvAmXWC1jgCFIY8RB.jpg",
      "popularity": 10.895
    }
  ],
  "total_pages": 26,
  "total_results": 507
}
"""

//https://api.themoviedb.org/3/movie/now_playing?api_key=5bd74c7a7bbfad5678fe1acd33fe732a&language=en-US&page=2
const val moviePage2 = """{
  "results": [
    {
      "popularity": 36.527,
      "vote_count": 14,
      "video": false,
      "poster_path": "/27eA9xGba61LtKr7gJRnhtDDgEP.jpg",
      "id": 651586,
      "adult": false,
      "backdrop_path": "/1iEDKZHREDetZcOoqioZewcHOw9.jpg",
      "original_language": "es",
      "original_title": "Superagente Makey",
      "genre_ids": [
        28,
        35
      ],
      "title": "Superagente Makey",
      "vote_average": 3.9,
      "overview": "Makey is a humble police officer that, unexpectedly, gets caught in the middle of a dangerous international drug operation in the Costa del Sol, Spain.",
      "release_date": "2020-07-17"
    },
    {
      "popularity": 35.906,
      "vote_count": 17,
      "video": false,
      "poster_path": "/syBQHeRpWabNFXFXWJpgVbBDAbX.jpg",
      "id": 625568,
      "adult": false,
      "backdrop_path": "/4DvZyY0y2TkwxUnSKl9gyM3qAOd.jpg",
      "original_language": "en",
      "original_title": "Unhinged",
      "genre_ids": [
        53
      ],
      "title": "Unhinged",
      "vote_average": 7.1,
      "overview": "A case of road rage turns into full-blown terror when an unstable driver follows a woman and her son.",
      "release_date": "2020-07-02"
    },
    {
      "popularity": 29.615,
      "vote_count": 519,
      "video": false,
      "poster_path": "/dqA2FCzz4OMmXLitKopzf476RVB.jpg",
      "id": 585244,
      "adult": false,
      "backdrop_path": "/21Q8bzu10YF9i4O5amBkJBombYo.jpg",
      "original_language": "en",
      "original_title": "I Still Believe",
      "genre_ids": [
        18,
        10402
      ],
      "title": "I Still Believe",
      "vote_average": 7.8,
      "overview": "The true-life story of Christian music star Jeremy Camp and his journey of love and loss that looks to prove there is always hope.",
      "release_date": "2020-03-12"
    },
    {
      "popularity": 28.371,
      "id": 484641,
      "video": false,
      "vote_count": 1249,
      "vote_average": 6.6,
      "title": "Anna",
      "release_date": "2019-06-19",
      "original_language": "fr",
      "original_title": "Anna",
      "genre_ids": [
        53,
        28
      ],
      "backdrop_path": "/oHDnvxSDOTsaYZSUibJjrEMWMLL.jpg",
      "adult": false,
      "overview": "Beneath Anna Poliatova's striking beauty lies a secret that will unleash her indelible strength and skill to become one of the world's most feared government assassins.",
      "poster_path": "/ybizg6RSYX3yvOQ2iLYucTrHGw2.jpg"
    },
    {
      "popularity": 29.327,
      "vote_count": 424,
      "video": false,
      "poster_path": "/uHpHzbHLSsVmAuuGuQSpyVDZmDc.jpg",
      "id": 556678,
      "adult": false,
      "backdrop_path": "/5GbkL9DDRzq3A21nR7Gkv6cFGjq.jpg",
      "original_language": "en",
      "original_title": "Emma.",
      "genre_ids": [
        35,
        18,
        10749
      ],
      "title": "Emma.",
      "vote_average": 6.9,
      "overview": "In 1800s England, a well-meaning but selfish young woman meddles in the love lives of her friends.",
      "release_date": "2020-02-13"
    },
    {
      "popularity": 35.025,
      "vote_count": 48,
      "video": false,
      "poster_path": "/zQFjMmE3K9AX5QrBL1SXIxYQ9jz.jpg",
      "id": 579583,
      "adult": false,
      "backdrop_path": "/5rwcd24GGltKiqdPT4G2dmchLr9.jpg",
      "original_language": "en",
      "original_title": "The King of Staten Island",
      "genre_ids": [
        35,
        18
      ],
      "title": "The King of Staten Island",
      "vote_average": 6.9,
      "overview": "Scott has been a case of arrested development ever since his firefighter father died when he was seven. He’s now reached his mid-20s having achieved little, chasing a dream of becoming a tattoo artist that seems far out of reach. As his ambitious younger sister heads off to college, Scott is still living with his exhausted ER nurse mother and spends his days smoking weed, hanging with the guys — Oscar, Igor and Richie — and secretly hooking up with his childhood friend Kelsey. But when his mother starts dating a loudmouth firefighter named Ray, it sets off a chain of events that will force Scott to grapple with his grief and take his first tentative steps toward moving forward in life.",
      "release_date": "2020-07-22"
    },
    {
      "popularity": 31.286,
      "vote_count": 1302,
      "video": false,
      "poster_path": "/gbPfvwBqbiHpQkYZQvVwB6MVauV.jpg",
      "id": 525661,
      "adult": false,
      "backdrop_path": "/nj84vpuUWdbmYktBzjiWn5Ny1ZF.jpg",
      "original_language": "en",
      "original_title": "Bombshell",
      "genre_ids": [
        18
      ],
      "title": "Bombshell",
      "vote_average": 6.8,
      "overview": "Bombshell is a revealing look inside the most powerful and controversial media empire of all time; and the explosive story of the women who brought down the infamous man who created it.",
      "release_date": "2019-12-13"
    },
    {
      "popularity": 27.518,
      "vote_count": 97,
      "video": false,
      "poster_path": "/3wZ0gxLqsPleneFSTZILmM3BE8Q.jpg",
      "id": 539181,
      "adult": false,
      "backdrop_path": "/vpUUznNzW85xo02H16RaSxRNS1.jpg",
      "original_language": "en",
      "original_title": "Relic",
      "genre_ids": [
        18,
        27
      ],
      "title": "Relic",
      "vote_average": 6.3,
      "overview": "When Edna—the elderly and widowed matriarch of the family—goes missing, her daughter and granddaughter travel to their remote family home to find her. Soon after her return, they start to discover a sinister presence haunting the house and taking control of Edna.",
      "release_date": "2020-07-03"
    },
    {
      "popularity": 30.811,
      "vote_count": 882,
      "video": false,
      "poster_path": "/qCDPKUMX5xrxxQY8XhGVCKO3fks.jpg",
      "id": 599975,
      "adult": false,
      "backdrop_path": "/zETkzgle7c6wAeW11snnVUBp67S.jpg",
      "original_language": "en",
      "original_title": "Countdown",
      "genre_ids": [
        27,
        53
      ],
      "title": "Countdown",
      "vote_average": 6.4,
      "overview": "A young nurse downloads an app that tells her she only has three days to live. With time ticking away and a mysterious figure haunting her, she must find a way to save her life before time runs out.",
      "release_date": "2019-10-24"
    },
    {
      "popularity": 29.027,
      "vote_count": 824,
      "video": false,
      "poster_path": "/2kNnf7BwRCEm4bcFkdiE0T4U25s.jpg",
      "id": 457335,
      "adult": false,
      "backdrop_path": "/fssCO59bqU5f0zngeYKex0g1vyb.jpg",
      "original_language": "en",
      "original_title": "Guns Akimbo",
      "genre_ids": [
        28,
        35
      ],
      "title": "Guns Akimbo",
      "vote_average": 6.4,
      "overview": "An ordinary guy suddenly finds himself forced to fight a gladiator-like battle for a dark website that streams the violence for viewers. Miles must fight heavily armed Nix and also save his kidnapped ex-girlfriend.",
      "release_date": "2019-09-09"
    },
    {
      "popularity": 30.095,
      "vote_count": 542,
      "video": false,
      "poster_path": "/mBBBXseq4k4dI63k06XIrsc02j8.jpg",
      "id": 542224,
      "adult": false,
      "backdrop_path": "/jEPEVO48hKQB0EUNFQOSv6qtKNW.jpg",
      "original_language": "en",
      "original_title": "Gretel & Hansel",
      "genre_ids": [
        14,
        27,
        53
      ],
      "title": "Gretel & Hansel",
      "vote_average": 6.3,
      "overview": "A long time ago in a distant fairy tale countryside, a young girl leads her little brother into a dark wood in desperate search of food and work, only to stumble upon a nexus of terrifying evil.",
      "release_date": "2020-01-30"
    },
    {
      "popularity": 33.993,
      "id": 458305,
      "video": false,
      "vote_count": 541,
      "vote_average": 5.9,
      "title": "Vivarium",
      "release_date": "2019-07-12",
      "original_language": "en",
      "original_title": "Vivarium",
      "genre_ids": [
        53,
        878,
        27
      ],
      "backdrop_path": "/jZ4YdDCnfK4cO13do9J4JaT8d2O.jpg",
      "adult": false,
      "overview": "A young woman and her fiancé are in search of the perfect starter home. After following a mysterious real estate agent to a new housing development, the couple finds themselves trapped in a maze of identical houses and forced to raise an otherworldly child.",
      "poster_path": "/myf3qzpeN0JbuFRPwSpJcz7rmAT.jpg"
    },
    {
      "popularity": 27.755,
      "vote_count": 65,
      "video": false,
      "poster_path": "/jBeL6pPUPo0wnyTmiuxPegcibPf.jpg",
      "id": 608195,
      "adult": false,
      "backdrop_path": "/8LI4OEQGY2DIBTQQeRjYVKfL9Dl.jpg",
      "original_language": "en",
      "original_title": "Ghosts of War",
      "genre_ids": [
        14,
        27,
        53,
        10752
      ],
      "title": "Ghosts of War",
      "vote_average": 5.5,
      "overview": "A group of World War II American soldiers encounter a supernatural enemy as they occupy a French castle previously under Nazi control.",
      "release_date": "2020-07-03"
    },
    {
      "popularity": 26.398,
      "vote_count": 149,
      "video": false,
      "poster_path": "/zaecdNcjcVDiOcDcgQCOgHELSo0.jpg",
      "id": 605804,
      "adult": false,
      "backdrop_path": "/su0huGwZfAmzPvUjnlG42LO9GFl.jpg",
      "original_language": "en",
      "original_title": "The Wretched",
      "genre_ids": [
        27
      ],
      "title": "The Wretched",
      "vote_average": 6.4,
      "overview": "A rebellious teenage boy, struggling with his parent's imminent divorce, encounters a terrifying evil after his next-door neighbor becomes possessed by an ancient witch that feasts on children.",
      "release_date": "2020-05-01"
    },
    {
      "popularity": 29.763,
      "vote_count": 648,
      "video": false,
      "poster_path": "/hJ6YEbrjFvToa5c7IiUqILoB6Je.jpg",
      "id": 552178,
      "adult": false,
      "backdrop_path": "/4ZSlTfkHtgTTupCaLbseXQQzZha.jpg",
      "original_language": "en",
      "original_title": "Dark Waters",
      "genre_ids": [
        18,
        53
      ],
      "title": "Dark Waters",
      "vote_average": 7.4,
      "overview": "A tenacious attorney uncovers a dark secret that connects a growing number of unexplained deaths to one of the world's largest corporations. In the process, he risks everything — his future, his family, and his own life — to expose the truth.",
      "release_date": "2019-11-22"
    },
    {
      "popularity": 27.366,
      "vote_count": 352,
      "video": false,
      "poster_path": "/vkwgzCBBiY3C1XEy0WakYfMOvnG.jpg",
      "id": 548473,
      "adult": false,
      "backdrop_path": "/iyn2JQsftaqhQlXYaCCvn8udM5i.jpg",
      "original_language": "en",
      "original_title": "Color Out of Space",
      "genre_ids": [
        27,
        878
      ],
      "title": "Color Out of Space",
      "vote_average": 6.1,
      "overview": "The Gardner family moves to a remote farmstead in rural New England to escape the hustle of the 21st century. They are busy adapting to their new life when a meteorite crashes into their front yard, melts into the earth, and infects both the land and the properties of space-time with a strange, otherworldly colour. To their horror, the family discovers this alien force is gradually mutating every life form that it touches—including them.",
      "release_date": "2020-01-24"
    },
    {
      "popularity": 31.75,
      "vote_count": 844,
      "video": false,
      "poster_path": "/izGX7npHEopDQvngYcxMJEfcFbj.jpg",
      "id": 461130,
      "adult": false,
      "backdrop_path": "/wlnDNMQlnwl5ETlVY6n9CEtR5s0.jpg",
      "original_language": "en",
      "original_title": "Code 8",
      "genre_ids": [
        28,
        80,
        18,
        878,
        53
      ],
      "title": "Code 8",
      "vote_average": 6.2,
      "overview": "In Lincoln City, some inhabitants have extraordinary abilities. Most live below the poverty line, under the close surveillance of a heavily militarized police force. Connor, a construction worker with powers, involves with a criminal gang to help his ailing mother. (Based on the short film “Code 8,” 2016.)",
      "release_date": "2019-12-06"
    },
    {
      "popularity": 30.165,
      "vote_count": 608,
      "video": false,
      "poster_path": "/4w1ItwCJCTtSi9nPfJC1vU6NIVg.jpg",
      "id": 413518,
      "adult": false,
      "backdrop_path": "/AdqOBPw4PdtzOcfEuQuZ8MNeTKb.jpg",
      "original_language": "it",
      "original_title": "Pinocchio",
      "genre_ids": [
        12,
        18,
        14,
        10751
      ],
      "title": "Pinocchio",
      "vote_average": 6.9,
      "overview": "In this live-action adaptation of the beloved fairytale, old woodcarver Geppetto fashions a wooden puppet, Pinocchio, who magically comes to life. Pinocchio longs for adventure and is easily led astray, encountering magical beasts, fantastical spectacles, while making friends and foes along his journey. However, his dream is to become a real boy, which can only come true if he finally changes his ways.",
      "release_date": "2019-12-19"
    },
    {
      "popularity": 27.331,
      "vote_count": 27,
      "video": false,
      "poster_path": "/9HMxF8jpRGsOIlPJkrK4UIM1LFU.jpg",
      "id": 571655,
      "adult": false,
      "backdrop_path": "/ij9dJtoRhtWNDyWLcgCf0EOTpdz.jpg",
      "original_language": "da",
      "original_title": "Valhalla",
      "genre_ids": [
        12,
        14
      ],
      "title": "Valhalla",
      "vote_average": 5.4,
      "overview": "The Viking children Røskva and Tjalfe embark on an adventurous journey from Midgard to Valhalla with the gods Thor and Loki. Life in Valhalla, however, turns out to be threatened by the dreaded Fenrir wolf and the god's barbaric archenemies, the Jotnar. Side by side with the gods the two children must fight to save Valhalla from the end of the world - Ragnarok.",
      "release_date": "2019-10-10"
    },
    {
      "popularity": 28.73,
      "vote_count": 294,
      "video": false,
      "poster_path": "/zGVbrulkupqpbwgiNedkJPyQum4.jpg",
      "id": 592350,
      "adult": false,
      "backdrop_path": "/9guoVF7zayiiUq5ulKQpt375VIy.jpg",
      "original_language": "ja",
      "original_title": "僕のヒーローアカデミア THE MOVIE ヒーローズ：ライジング",
      "genre_ids": [
        28,
        16
      ],
      "title": "My Hero Academia: Heroes Rising",
      "vote_average": 8.8,
      "overview": "Class 1-A visits Nabu Island where they finally get to do some real hero work. The place is so peaceful that it's more like a vacation … until they're attacked by a villain with an unfathomable Quirk! His power is eerily familiar, and it looks like Shigaraki had a hand in the plan. But with All Might retired and citizens' lives on the line, there's no time for questions. Deku and his friends are the next generation of heroes, and they're the island's only hope.",
      "release_date": "2019-12-20"
    }
  ],
  "page": 2,
  "total_results": 693,
  "dates": {
    "maximum": "2020-08-03",
    "minimum": "2020-06-16"
  },
  "total_pages": 35
}"""

//https://api.themoviedb.org/3/movie/now_playing?api_key=5bd74c7a7bbfad5678fe1acd33fe732a&language=en-US&page=1
const val moviePage1 = """{
  "results": [
    {
      "popularity": 145.49,
      "vote_count": 770,
      "video": false,
      "poster_path": "/kjMbDciooTbJPofVXgAoFjfX8Of.jpg",
      "id": 516486,
      "adult": false,
      "backdrop_path": "/xXBnM6uSTk6qqCf0SRZKXcga9Ba.jpg",
      "original_language": "en",
      "original_title": "Greyhound",
      "genre_ids": [
        28,
        18,
        10752
      ],
      "title": "Greyhound",
      "vote_average": 7.5,
      "overview": "A first-time captain leads a convoy of allied ships carrying thousands of soldiers across the treacherous waters of the “Black Pit” to the front lines of WW2. With no air cover protection for 5 days, the captain and his convoy must battle the surrounding enemy Nazi U-boats in order to give the allies a chance to win the war.",
      "release_date": "2020-06-19"
    },
    {
      "popularity": 120.046,
      "vote_count": 315,
      "video": false,
      "poster_path": "/jHo2M1OiH9Re33jYtUQdfzPeUkx.jpg",
      "id": 385103,
      "adult": false,
      "backdrop_path": "/fKtYXUhX5fxMxzQfyUcQW9Shik6.jpg",
      "original_language": "en",
      "original_title": "Scoob!",
      "genre_ids": [
        12,
        16,
        35,
        9648,
        10751
      ],
      "title": "Scoob!",
      "vote_average": 7.7,
      "overview": "In Scooby-Doo’s greatest adventure yet, see the never-before told story of how lifelong friends Scooby and Shaggy first met and how they joined forces with young detectives Fred, Velma, and Daphne to form the famous Mystery Inc. Now, with hundreds of cases solved, Scooby and the gang face their biggest, toughest mystery ever: an evil plot to unleash the ghost dog Cerberus upon the world. As they race to stop this global “dogpocalypse,” the gang discovers that Scooby has a secret legacy and an epic destiny greater than anyone ever imagined.",
      "release_date": "2020-07-08"
    },
    {
      "popularity": 93.701,
      "vote_count": 26678,
      "video": false,
      "poster_path": "/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
      "id": 27205,
      "adult": false,
      "backdrop_path": "/s3TBrRGB1iav7gFOCNx3H31MoES.jpg",
      "original_language": "en",
      "original_title": "Inception",
      "genre_ids": [
        28,
        12,
        878
      ],
      "title": "Inception",
      "vote_average": 8.3,
      "overview": "Cobb, a skilled thief who commits corporate espionage by infiltrating the subconscious of his targets is offered a chance to regain his old life as payment for a task considered to be impossible: \"inception\", the implantation of another person's idea into a target's subconscious.",
      "release_date": "2010-07-15"
    },
    {
      "popularity": 83.582,
      "vote_count": 8645,
      "video": false,
      "poster_path": "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
      "id": 496243,
      "adult": false,
      "backdrop_path": "/ApiBzeaa95TNYliSbQ8pJv4Fje7.jpg",
      "original_language": "ko",
      "original_title": "기생충",
      "genre_ids": [
        35,
        18,
        53
      ],
      "title": "Parasite",
      "vote_average": 8.5,
      "overview": "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
      "release_date": "2019-05-30"
    },
    {
      "popularity": 78.123,
      "vote_count": 236,
      "video": false,
      "poster_path": "/hPkqY2EMqWUnFEoedukilIUieVG.jpg",
      "id": 531876,
      "adult": false,
      "backdrop_path": "/n1RohH2VoK1CdVI2fXvcP19dSlm.jpg",
      "original_language": "en",
      "original_title": "The Outpost",
      "genre_ids": [
        28,
        18,
        36,
        10752
      ],
      "title": "The Outpost",
      "vote_average": 6.6,
      "overview": "A small unit of U.S. soldiers, alone at the remote Combat Outpost Keating, located deep in the valley of three mountains in Afghanistan, battles to defend against an overwhelming force of Taliban fighters in a coordinated attack. The Battle of Kamdesh, as it was known, was the bloodiest American engagement of the Afghan War in 2009 and Bravo Troop 3-61 CAV became one of the most decorated units of the 19-year conflict.",
      "release_date": "2020-06-24"
    },
    {
      "popularity": 84.942,
      "vote_count": 5291,
      "video": false,
      "poster_path": "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
      "id": 454626,
      "adult": false,
      "backdrop_path": "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
      "original_language": "en",
      "original_title": "Sonic the Hedgehog",
      "genre_ids": [
        28,
        35,
        878,
        10751
      ],
      "title": "Sonic the Hedgehog",
      "vote_average": 7.5,
      "overview": "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
      "release_date": "2020-02-12"
    },
    {
      "popularity": 69.157,
      "vote_count": 72,
      "video": false,
      "poster_path": "/3ynPnBXQVT2Y0s19fDIPlWKUlxH.jpg",
      "id": 587496,
      "adult": false,
      "backdrop_path": "/2gBMBfmQvHeOQQ5PiKiuxVdGs77.jpg",
      "original_language": "en",
      "original_title": "The Rental",
      "genre_ids": [
        18,
        27,
        53
      ],
      "title": "The Rental",
      "vote_average": 5.8,
      "overview": "Two couples on an oceanside getaway grow suspicious that the host of their seemingly perfect rental house may be spying on them. Before long, what should have been a celebratory weekend trip turns into something far more sinister.",
      "release_date": "2020-07-23"
    },
    {
      "popularity": 65.319,
      "vote_count": 328,
      "video": false,
      "poster_path": "/MBiKqTsouYqAACLYNDadsjhhC0.jpg",
      "id": 486589,
      "adult": false,
      "backdrop_path": "/bga3i5jcejBekr2FCGJga1fYCh.jpg",
      "original_language": "en",
      "original_title": "Red Shoes and the Seven Dwarfs",
      "genre_ids": [
        16,
        10749,
        10751
      ],
      "title": "Red Shoes and the Seven Dwarfs",
      "vote_average": 7.3,
      "overview": "Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.",
      "release_date": "2019-07-25"
    },
    {
      "popularity": 71.116,
      "vote_count": 12,
      "video": false,
      "poster_path": "/aVbqhqYtlxwEGihTEhewZAgDOCX.jpg",
      "id": 489326,
      "adult": false,
      "backdrop_path": "/dFB6Tiy3z2xRLbnEUB5ocApT5xG.jpg",
      "original_language": "en",
      "original_title": "Mortal",
      "genre_ids": [
        28,
        12,
        14
      ],
      "title": "Mortal",
      "vote_average": 5.2,
      "overview": "Fantasy adventure origin story about a young man discovering he has God-like powers based on ancient Norwegian mythology.",
      "release_date": "2020-02-28"
    },
    {
      "popularity": 51.5,
      "vote_count": 2778,
      "video": false,
      "poster_path": "/f4aul3FyD3jv3v4bul1IrkWZvzq.jpg",
      "id": 508439,
      "adult": false,
      "backdrop_path": "/xFxk4vnirOtUxpOEWgA1MCRfy6J.jpg",
      "original_language": "en",
      "original_title": "Onward",
      "genre_ids": [
        12,
        16,
        35,
        14,
        10751
      ],
      "title": "Onward",
      "vote_average": 7.9,
      "overview": "In a suburban fantasy world, two teenage elf brothers embark on an extraordinary quest to discover if there is still a little magic left out there.",
      "release_date": "2020-02-29"
    },
    {
      "popularity": 42.803,
      "vote_count": 91,
      "video": false,
      "poster_path": "/akHIQu8W3rOgT28r25ggXaKcQIr.jpg",
      "id": 480857,
      "adult": false,
      "backdrop_path": "/e7tjpKP36Xd6BzyTMZo4AUe86VM.jpg",
      "original_language": "en",
      "original_title": "Radioactive",
      "genre_ids": [
        18,
        36,
        10749
      ],
      "title": "Radioactive",
      "vote_average": 5.9,
      "overview": "Tells the story of Nobel Prize winner Marie Curie and her extraordinary scientific discoveries — through the prism of her marriage to husband Pierre — and the seismic and transformative effects their discovery of radium had on the 20th century.",
      "release_date": "2020-03-11"
    },
    {
      "popularity": 39.326,
      "vote_count": 21,
      "video": false,
      "poster_path": "/saxPKOrN6rNScTicLpBDZw58Cw.jpg",
      "id": 586954,
      "adult": false,
      "backdrop_path": "/poKtpiQ0akacXzXohWmtb0Z7nFD.jpg",
      "original_language": "de",
      "original_title": "Latte Igel und der magische Wasserstein",
      "genre_ids": [
        12,
        16,
        35,
        10751
      ],
      "title": "Latte and the Magic Waterstone",
      "vote_average": 6.8,
      "overview": "When a greedy bear steals a magic stone to keep the forest’s water for himself, a brave hedgehog and a timid squirrel must work together to retrieve it.",
      "release_date": "2019-12-11"
    },
    {
      "popularity": 37.714,
      "vote_count": 1172,
      "video": false,
      "poster_path": "/wxPhn4ef1EAo5njxwBkAEVrlJJG.jpg",
      "id": 514847,
      "adult": false,
      "backdrop_path": "/naXUDz0VGK7aaPlEpsuYW8kNVsr.jpg",
      "original_language": "en",
      "original_title": "The Hunt",
      "genre_ids": [
        28,
        27,
        53
      ],
      "title": "The Hunt",
      "vote_average": 6.6,
      "overview": "Twelve strangers wake up in a clearing. They don't know where they are—or how they got there. In the shadow of a dark internet conspiracy theory, ruthless elitists gather at a remote location to hunt humans for sport. But their master plan is about to be derailed when one of the hunted turns the tables on her pursuers.",
      "release_date": "2020-03-11"
    },
    {
      "popularity": 45.072,
      "vote_count": 17511,
      "video": false,
      "poster_path": "/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
      "id": 603,
      "adult": false,
      "backdrop_path": "/ByDf0zjLSumz1MP1cDEo2JWVtU.jpg",
      "original_language": "en",
      "original_title": "The Matrix",
      "genre_ids": [
        28,
        878
      ],
      "title": "The Matrix",
      "vote_average": 8.1,
      "overview": "Set in the 22nd century, The Matrix tells the story of a computer hacker who joins a group of underground insurgents fighting the vast and powerful computers who now rule the earth.",
      "release_date": "1999-03-30"
    },
    {
      "popularity": 30.58,
      "vote_count": 82,
      "video": false,
      "poster_path": "/yHsu8swJSgqDrsPiu7adBjOPLlp.jpg",
      "id": 606679,
      "adult": false,
      "backdrop_path": "/aVX9a54YFmrcEHATubpuFQQKn5L.jpg",
      "original_language": "en",
      "original_title": "The High Note",
      "genre_ids": [
        35,
        18,
        10402,
        10749
      ],
      "title": "The High Note",
      "vote_average": 7.5,
      "overview": "Set in the dazzling world of the LA music scene comes the story of Grace Davis, a superstar whose talent, and ego, have reached unbelievable heights. Maggie is Grace’s overworked personal assistant who’s stuck running errands, but still aspires to her childhood dream of becoming a music producer. When Grace’s manager presents her with a choice that could alter the course of her career, Maggie and Grace come up with a plan that could change their lives forever.",
      "release_date": "2020-06-10"
    },
    {
      "popularity": 36.34,
      "vote_count": 8,
      "video": false,
      "poster_path": "/qSAwS1U4erAVaCGKj3aAxhqy10k.jpg",
      "id": 614696,
      "adult": false,
      "backdrop_path": "/aD3f5anIW0vukVYXAQ1aCALOG5y.jpg",
      "original_language": "ko",
      "original_title": "#살아있다",
      "genre_ids": [
        28,
        27,
        53
      ],
      "title": "#Alive",
      "vote_average": 6.6,
      "overview": "A disaster film about a city infected by a mysterious virus and the survivor who is isolated in the city",
      "release_date": "2020-06-24"
    },
    {
      "popularity": 34.685,
      "vote_count": 2039,
      "video": false,
      "poster_path": "/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg",
      "id": 522938,
      "adult": false,
      "backdrop_path": "/3IHqwENGxOb36Jgeot4XHs3BTxz.jpg",
      "original_language": "en",
      "original_title": "Rambo: Last Blood",
      "genre_ids": [
        28,
        18,
        53
      ],
      "title": "Rambo: Last Blood",
      "vote_average": 6.3,
      "overview": "After fighting his demons for decades, John Rambo now lives in peace on his family ranch in Arizona, but his rest is interrupted when Gabriela, the granddaughter of his housekeeper María, disappears after crossing the border into Mexico to meet her biological father. Rambo, who has become a true father figure for Gabriela over the years, undertakes a desperate and dangerous journey to find her.",
      "release_date": "2019-09-19"
    },
    {
      "popularity": 40.02,
      "vote_count": 27,
      "video": false,
      "poster_path": "/7S9RvfMBWSTlUZ4VbxDY7oLeenk.jpg",
      "id": 581392,
      "adult": false,
      "backdrop_path": "/aWsVwgG324dFQS5xHEjB7OCOT9w.jpg",
      "original_language": "ko",
      "original_title": "반도",
      "genre_ids": [
        28,
        27,
        53
      ],
      "title": "Peninsula",
      "vote_average": 6.4,
      "overview": "Peninsula takes place four years after Train to Busan as the characters fight to escape the land that is in ruins due to an unprecedented disaster.",
      "release_date": "2020-07-15"
    },
    {
      "popularity": 38.036,
      "vote_count": 3835,
      "video": false,
      "poster_path": "/2oRRTPNtozgPhOa9CYZiVl4GRQ5.jpg",
      "id": 396535,
      "adult": false,
      "backdrop_path": "/5H8ftwrh0OzQ7QxTOixNqoMcIZs.jpg",
      "original_language": "ko",
      "original_title": "부산행",
      "genre_ids": [
        28,
        27,
        53
      ],
      "title": "Train to Busan",
      "vote_average": 7.7,
      "overview": "Martial law is declared when a mysterious viral outbreak pushes Korea into a state of emergency. Those on an express train to Busan, a city that has successfully fended off the viral outbreak, must fight for their own survival…",
      "release_date": "2016-07-20"
    },
    {
      "popularity": 50.96,
      "vote_count": 7,
      "video": false,
      "poster_path": "/vIGwX6SWyOfUX0hKdw5JB2vLbM2.jpg",
      "id": 523977,
      "adult": false,
      "backdrop_path": "/idV2S95f6iBluiGEI808oeRiGFQ.jpg",
      "original_language": "en",
      "original_title": "Summerland",
      "genre_ids": [
        18,
        10752
      ],
      "title": "Summerland",
      "vote_average": 9.1,
      "overview": "A woman during the Second World War opens her heart to an evacuee after initially resolving to be rid of him.",
      "release_date": "2020-07-24"
    }
  ],
  "page": 1,
  "total_results": 693,
  "dates": {
    "maximum": "2020-08-03",
    "minimum": "2020-06-16"
  },
  "total_pages": 35
}"""

const val moviePageInvalid = """{
  "results": [],
  "page": 100,
  "total_results": 0,
  "dates": {
    "maximum": "2020-08-03",
    "minimum": "2020-06-16"
  },
  "total_pages": 99
}"""
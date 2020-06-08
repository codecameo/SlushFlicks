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
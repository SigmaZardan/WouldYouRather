package com.example.wouldyourather.data

import com.example.wouldyourather.model.Option
import com.example.wouldyourather.model.Question


val questions: List<Question> = listOf(

    Question(
        question = "Would you rather",
        options = Pair(
            Option(optionId = 1, "Receive a surprise gift", "The joy of unexpected presents."),
            Option(
                optionId = 2,
                "Plan a gift together",
                "Ensuring it's exactly what you both want."
            )
        )
    ),
    Question(
        question = "What's your preference?",
        options = Pair(
            Option(
                optionId = 1,
                "A cozy night in",
                "Snuggling up with your favorite movie and each other."
            ),
            Option(
                optionId = 2,
                "A night out on the town",
                "Dressing up and hitting the town for a memorable evening."
            )
        )
    ),
    Question(
        question = "What will you prefer?",
        options = Pair(
            Option(
                optionId = 1,
                "Sex on the couch",
                "Couch was awesome last time right ?"
            ),
            Option(
                optionId = 2,
                "Sex on the floor",
                "On the floor in your room."
            )
        )
    ),
    Question(
        question = "Would you rather",
        options = Pair(
            Option(
                optionId = 1,
                "Cook a romantic dinner together",
                "Bonding over preparing a delicious meal."
            ),
            Option(
                optionId = 2,
                "Go out to a fancy restaurant",
                "Enjoying a luxurious dining experience."
            )
        )
    ),
    Question(
        question = "Would you rather",
        options = Pair(
            Option(
                optionId = 1,
                "Cook a romantic dinner together",
                "Bonding over preparing a delicious meal."
            ),
            Option(
                optionId = 2,
                "Go out to a fancy restaurant",
                "Enjoying a luxurious dining experience."
            )
        )
    ),
    Question(
        question = "Would you rather",
        options = Pair(
            Option(
                optionId = 1,
                "kiss your partner on the nose",
                "Nose is your favorite, right?"
            ),
            Option(
                optionId = 2,
                "kiss your partner on the lips",
                "He is a pro at kissing you. Remember that."
            )
        )
    ),
    Question(
        question = "Would you rather",
        options = Pair(
            Option(
                optionId = 1,
                "have sex all day long",
                "Might be a day marked on the calendar."
            ),
            Option(
                optionId = 2,
                "have exercise for an hour",
                "Having your bubble-shaped body to allure your husband in bed."
            )
        )
    ),
    Question(
        question = "Would you rather go to a",
        options = Pair(
            Option(
                optionId = 1,
                "naturally blessed place with your partner",
                "Kaskikot, Godawari, Khastey, Rupakot, Basundhara Park."
            ),
            Option(optionId = 2, "holy place with your partner", "Karputar, Pundikot.")
        )
    ),
    Question(
        question = "Would you rather",
        options = Pair(
            Option(
                optionId = 1,
                "ride coco with the shock expansion",
                "Mind fresh, coco loves you."
            ),
            Option(
                optionId = 2,
                "ride your husband",
                "Remember the love hormones flowing, good sleep, bonding. Your husband can sleep well."
            )
        )
    ),
    Question(
        question = "Would you rather go on a",
        options = Pair(
            Option(
                optionId = 1,
                "photo shoot with pink and white flowers with your partner",
                "Capturing memories with an instant camera."
            ),
            Option(
                optionId = 2,
                "watch Jawan movie with your partner",
                "Cozy time with a heater beside you, enjoying the movie and your husband shouting at every action scene."
            )
        )
    )
)

val TOTAL_QUESTIONS = questions.size

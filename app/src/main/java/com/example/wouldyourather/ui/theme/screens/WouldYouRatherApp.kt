package com.example.wouldyourather.ui.theme.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wouldyourather.R
import com.example.wouldyourather.ui.theme.viewmodel.WouldYouRatherViewModel

@Preview(showSystemUi = true)
@Composable
fun WouldYouRatherApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            WouldYouRatherTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .statusBarsPadding()
                .navigationBarsPadding()

        ) {
            QuestionsAndOptionCardItem(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .fillMaxSize(),
            )

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WouldYouRatherTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.splash_logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayMedium,
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview
@Composable
fun WouldYouRatherTopAppBarPreview() {
    WouldYouRatherTopAppBar()

}


@Composable
fun QuestionsAndOptionCardItem(
    modifier: Modifier = Modifier,
    viewModel: WouldYouRatherViewModel = viewModel()
) {
    // what are the things that change in the app ?
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            uiState.currentQuestion,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
        ) // typography i will look into it when i perform the material design things
        // bigger font size will be used
        Spacer(modifier = Modifier.weight(1f))
        OptionCardItem(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(
                    MaterialTheme.colorScheme.primary
                ),
            option = uiState.currentOption1Text,
            onOptionCardClicked = {
                viewModel.showOptionDescription()
            }
        )
        Text(
            "OR", style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_small), bottom = dimensionResource(
                    id = R.dimen.padding_small
                )
            )
        )
        OptionCardItem(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(
                    MaterialTheme.colorScheme.primary
                ),
            option = uiState.currentOption2Text,
            onOptionCardClicked = {
                viewModel.showOptionDescription()
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        RowButtonsItem(onPrevButtonClick = {
            viewModel.displayPrevQuestion()
        },
            onResetOptionButtonClick = {
                viewModel.showOptionTextAgain()
            },
            onNextButtonClick = {
                viewModel.displayNextQuestion()
            })

    }

    if (uiState.isGameOver) {
        FinalScoreDialog(onPlayAgain = { viewModel.resetGame() })
    }
}

@Composable
private fun FinalScoreDialog(
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(R.string.your_husband_loves_you_a_lot)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onPlayAgain()
            }) {
                Text(text = "Play Again")
            }
        }
    )


}

@Composable
fun RowButtonsItem(
    onPrevButtonClick: () -> Unit,
    onResetOptionButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onPrevButtonClick
        ) {
            Text("Prev")
        }
        Button(
            onClick = onResetOptionButtonClick
        ) {
            Text("Show Options Again")
        }
        Button(
            onClick = onNextButtonClick
        ) {
            Text("Next")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsAndOptionCardItemPreview() {
    QuestionsAndOptionCardItem(
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
fun OptionCardItem(
    modifier: Modifier = Modifier,
    option: String,
    onOptionCardClicked: () -> Unit
) {
    // a big box with different back ground color 
    // and also text on top of it showing the answers
    // i have the option here
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .clickable {
                onOptionCardClicked()
            }
            .testTag("OptionCard"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            option,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            color = MaterialTheme.colorScheme.onPrimary
        )
        // option will have the same typography as the question but the size will be smaller somehow 

    }
}


@Preview(showBackground = true)
@Composable
fun OptionCardItemPreview() {
    OptionCardItem(
        modifier = Modifier
            .size(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                Color.LightGray
            ),
        option = "anything",
        onOptionCardClicked = {}
    )
}


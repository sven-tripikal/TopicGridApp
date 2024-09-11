package com.sventripikal.topicgridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sventripikal.topicgridapp.data.Datasource.topics
import com.sventripikal.topicgridapp.model.Topic
import com.sventripikal.topicgridapp.ui.theme.TopicGridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopicGridAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopicsApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


// main app
@Composable
fun TopicsApp(
    modifier: Modifier = Modifier
) {
//  layout direction value
    val layoutDirection = LocalLayoutDirection.current

//  create padding for entire surface layout
    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
//      Lazy Grid from List of Topics (Datasource.topics)
        TopicLazyGrid(topics)
    }
}


// Lazy Grid from List of Topics composable
@Composable
fun TopicLazyGrid(
    listOfTopics: List<Topic>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),   // 2 column wide top/down grid
        contentPadding = PaddingValues(8.dp),   // entire outer content spacing
        verticalArrangement = Arrangement.spacedBy(8.dp),   // vertical spacing between items
        horizontalArrangement = Arrangement.spacedBy(8.dp)  // horizontal spacing between items
    ) {
//      create GridItem for each topic
        items(listOfTopics) { topic ->
            TopicGridItem(topic)
        }
    }
}


// Grid Item composable for each individual Topic
@Composable
fun TopicGridItem(
    topic: Topic,
    modifier: Modifier = Modifier
) {
//  card size 180x68
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.size(width = 180.dp, height = 68.dp)
    ) {
//      fill entire card with a row
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
//          image size 68x68 / cropped
            Image(
                painter = painterResource(topic.image),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(topic.name),
                modifier = Modifier.size(width = 68.dp, height = 68.dp)
            )

//          topic column composable filling rest of card space
            TopicColumnInfo(topic = topic, modifier = Modifier.weight(1f))
        }
    }
}


// Topic Column composable filling rest of card space
@Composable
fun TopicColumnInfo(
    topic: Topic,
    modifier: Modifier = Modifier
) {
//  spacing top,left,right 16 / align content left
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
//     topic name
        Text(
            text = stringResource(topic.name),
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodyMedium
        )

//      vertical spacing 8
        Spacer(modifier = Modifier.height(8.dp))

//      row for icon & number of courses / centered
        IconNumberRow(topic)
    }
}


// row for icon & number of courses / centered
@Composable
private fun IconNumberRow(topic: Topic) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
//      grain dotted icon
        Icon(
            painter = painterResource(R.drawable.ic_grain),
            contentDescription = stringResource(R.string.grain_dotted_icon)
        )

//      horizontal spacing 8
        Spacer(modifier = Modifier.width(8.dp))

//      number of courses text
        Text(
            text = topic.numberOfCourses.toString(),
            style = MaterialTheme.typography.labelMedium
        )
    }
}


// preview for an individual TopicGridItem
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TopicGridItemPreview() {
    TopicGridAppTheme {
//      Photography Topic preview
        TopicGridItem(topics[topics.lastIndex.dec()])
    }
}


// preview for main TopicsApp with Grid layout
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TopicsAppPreview() {
    TopicGridAppTheme {
//      main app preview
        TopicsApp()
    }
}

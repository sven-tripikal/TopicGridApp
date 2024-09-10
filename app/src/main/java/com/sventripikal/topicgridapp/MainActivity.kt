package com.sventripikal.topicgridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
                    TopicGridItem(
                        topics[topics.lastIndex.dec()],
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Grid Item composable for each individual Topic
@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
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


// topic column composable filling rest of card space
@Composable
fun TopicColumnInfo(topic: Topic, modifier: Modifier = Modifier) {
//  padding top,left,right 16 / align left
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

//      spacer height 8
        Spacer(modifier = Modifier.height(8.dp))

//      row for icon & number of courses / centered
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

//          grain dotted icon
            Icon(
                painter = painterResource(R.drawable.ic_grain),
                contentDescription = stringResource(R.string.grain_dotted_icon)
            )

//          spacer width 8
            Spacer(modifier = Modifier.width(8.dp))

//          number of courses text
            Text(
                text = topic.numberOfCourses.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}


// preview composable for TopicGridItem
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TopicGridItemPreview() {
    TopicGridAppTheme {
//      preview Photography Topic
        TopicGridItem(topics[topics.lastIndex.dec()])
    }
}

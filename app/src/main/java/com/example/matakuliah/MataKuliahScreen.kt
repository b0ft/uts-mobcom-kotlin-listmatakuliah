package com.example.matakuliah

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matakuliah.model.MataKuliah
import com.example.matakuliah.model.MataKuliahRepository

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MataKuliahList(
    mataKuliah: List<MataKuliah>,
//    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        ),
        exit = fadeOut()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = contentPadding
        ) {
            itemsIndexed(mataKuliah) {index, mataKuliah ->
                MataKuliahItem(
                    mataKuliah = mataKuliah,
                    modifier = Modifier
                        .padding(12.dp, vertical = 4.dp)
                        .animateEnterExit(
                            enter = slideInHorizontally(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessMedium,
                                    dampingRatio = Spring.DampingRatioMediumBouncy
                                ),
                                initialOffsetX = { it * (index + 1)}
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun MataKuliahItem(
    mataKuliah : MataKuliah,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 64.dp)
        ) {
            Box(
                modifier = Modifier.size(72.dp)
            ) {
                Image(
                    painter = painterResource(mataKuliah.imageRes),
                    contentDescription = null,
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(mataKuliah.matkulRes),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp)

                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(mataKuliah.sksRes),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp)
                )
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun MataKuliahItemPreview() {
//    MatakuliahTheme {
//        MataKuliahItem("Tes")
//    }
//}

@Preview("List")
@Composable
fun MataKuliahPreview() {
    MataKuliahList(mataKuliah = MataKuliahRepository.mataKuliah)
}


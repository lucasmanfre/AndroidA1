/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.up.edu.rgm33950873.ui.item

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.up.edu.rgm33950873.InventoryTopAppBar
import br.up.edu.rgm33950873.R
import br.up.edu.rgm33950873.ui.AppViewModelProvider
import br.up.edu.rgm33950873.ui.navigation.NavigationDestination
import br.up.edu.rgm33950873.ui.theme.InventoryTheme

object ItemEditDestination : NavigationDestination {
    override val route = "item_edit"
    override val titleRes = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = { },
            onSaveClick = { },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemEditScreenPreview() {
    InventoryTheme {
        ItemEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}
package com.uniroot.app.ui

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.CloudDownload
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.DeveloperBoard
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.RemoveCircleOutline
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material.icons.rounded.Terminal
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarArrangement
import androidx.compose.material3.ShortNavigationBarDefaults
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TonalToggleButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uniroot.app.BuildConfig
import com.uniroot.app.R
import com.uniroot.app.engine.DeviceProfile
import com.uniroot.app.engine.RunLogFile
import com.uniroot.app.newmethod.KsudClassicProfiles
import com.uniroot.app.newmethod.KsudNextProfile
import java.io.File

enum class UniTab(val labelRes: Int) {
    UNIROOT(R.string.tab_uniroot),
    LOGS(R.string.tab_logs),
}

data class UniUiState(
    val deviceName: String = "",
    val kernelRelease: String = "",
    val socName: String = "",
    val kernelSupported: Boolean = false,
    val rooted: Boolean = false,
    val running: Boolean = false,
    val advancedVisible: Boolean = false,
    val shizukuEnabled: Boolean = false,
    val profiles: List<DeviceProfile> = emptyList(),
    val selectedProfileName: String? = null,
    val executionSheetVisible: Boolean = false,
    val executionSheetDismissible: Boolean = false,
    val logLines: List<String> = emptyList(),
    val tab: UniTab = UniTab.UNIROOT,
    val useLatestKsu: Boolean = false,
    val latestKsuTag: String = "",
    val autoRootOnBoot: Boolean = false,
    val rmgStatus: String = "",
    val ksuNextMode: Boolean = false,
    val runLogs: List<RunLogFile> = emptyList(),
    val logViewerFile: RunLogFile? = null,
    val logViewerContent: String = "",
    val instantRootEnabled: Boolean = false,
    val instantRootInstalled: Boolean = false,
    val instantRootOfferVisible: Boolean = false,
    val keepInstantRootVisible: Boolean = false,
    val instantRootInstallVisible: Boolean = false,
    val instantRootLogVisible: Boolean = false,
    val instantRootLogContent: String = "",
    val superuserNeededVisible: Boolean = false,
    val injectConfirmVisible: Boolean = false,
    // NEW METHOD (fast, DFRoot engine): fully separated from the old flow.
    val newMethod: Boolean = false,
    val newMethodBootEnabled: Boolean = false,
    val newMethodAutoSoftReboot: Boolean = true,
    val newMethodProgress: Int = 0,
    val newMethodStage: String = "",
    // New méthode + KernelSU Next : profil ksud embarqué choisi (3.3.0, 3.4.0…).
    val ksudNextProfiles: List<KsudNextProfile> = emptyList(),
    val ksudNextProfileId: String = "",
    // New méthode + KernelSU classic : profil classic (bundled / téléchargé).
    val ksudClassicProfiles: List<KsudClassicProfiles.ClassicProfile> = emptyList(),
    val ksudClassicProfileId: String = "",
    // DEV — updater ksud Next (télécharge + patche + crée un profil).
    val ksudUpdateRunning: Boolean = false,
    val ksudUpdateLog: String = "",
    val ksudUpdateSheetVisible: Boolean = false,
    val ksudUpdateProgress: Int = 0,
    val ksudUpdateStage: String = "",

    val deviceInfoRows: List<Pair<String, String>> = emptyList(),
    val rootStateRow: String = "",
    val rootDetailRow: String = "",
    val selinuxRow: String = "",
)

interface UniActions {
    fun onRun()
    fun onDisableRoot()
    fun onInstantRootEnable()
    fun onInstantRootNotNow()
    fun onKeepInstantRoot(keep: Boolean)
    fun onInstantRootEnabledChanged(enabled: Boolean)
    fun onUninstallHelper()
    fun onInstantRootInstallNow()
    fun onInstantRootInstallLater()
    fun onOpenInstantRootLog()
    fun onCloseInstantRootLog()
    fun onOpenKsuManager()
    fun onDismissSuperuserNeeded()
    fun onInjectContinue()
    fun onInjectCancel()
    fun onOpenHelper()
    fun onCloseExecutionSheet()
    fun onTabSelected(tab: UniTab)
    fun onToggleAdvanced()
    fun onCopyLogs()
    fun onProfileSelected(index: Int)
    fun onShizukuChanged(enabled: Boolean)
    fun onKsuFlavorChanged(next: Boolean)
    fun onResetProfiles()
    fun onProfileSave(profile: DeviceProfile, originalName: String?)
    fun onProfileDelete(name: String)
    fun onUseLatestKsuChanged(enabled: Boolean)
    fun onDownloadLatestKsu()
    fun onAutoRootChanged(enabled: Boolean)
    fun onCheckRmg()
    fun onRunLogOpen(file: RunLogFile)
    fun onRunLogShare(file: RunLogFile)
    fun onRunLogDelete(file: RunLogFile)
    fun onRunLogViewerClose()
    fun onRootMethodChanged(newMethod: Boolean)
    fun onNewMethodBootChanged(enabled: Boolean)
    fun onNewMethodSoftRebootChanged(enabled: Boolean)
    fun onKsudNextProfileChanged(id: String)
    fun onUpdateKsud()
    fun onCloseKsudUpdateSheet()
    fun onDeleteKsudProfile(id: String, classic: Boolean)
    fun onKsudClassicProfileChanged(id: String)
}

// ---------------------------------------------------------------------
// Material 3 Expressive Theme configuration
// ---------------------------------------------------------------------

private val ExpressiveLightColorScheme = lightColorScheme(
    primary = Color(0xFF005AC1),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD8E2FF),
    onPrimaryContainer = Color(0xFF001A41),
    secondary = Color(0xFF575E71),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDBE2F9),
    onSecondaryContainer = Color(0xFF141B2C),
    tertiary = Color(0xFF715573),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFBD7FC),
    onTertiaryContainer = Color(0xFF29132D),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFDFBFF),
    onBackground = Color(0xFF1A1B1F),
    surface = Color(0xFFFDFBFF),
    onSurface = Color(0xFF1A1B1F),
    surfaceVariant = Color(0xFFE1E2EC),
    onSurfaceVariant = Color(0xFF44474F),
    outline = Color(0xFF74777F),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF7F3F9),
    surfaceContainer = Color(0xFFF1EDF4),
    surfaceContainerHigh = Color(0xFFEBE7EE),
    surfaceContainerHighest = Color(0xFFE5E1E8),
)

private val ExpressiveDarkColorScheme = darkColorScheme(
    primary = Color(0xFFAEC6FF),
    onPrimary = Color(0xFF002E69),
    primaryContainer = Color(0xFF004494),
    onPrimaryContainer = Color(0xFFD8E2FF),
    secondary = Color(0xFFBFC6DC),
    onSecondary = Color(0xFF293041),
    secondaryContainer = Color(0xFF3F4759),
    onSecondaryContainer = Color(0xFFDBE2F9),
    tertiary = Color(0xFFDEBCDF),
    onTertiary = Color(0xFF402843),
    tertiaryContainer = Color(0xFF583E5B),
    onTertiaryContainer = Color(0xFFFBD7FC),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF111318),
    onBackground = Color(0xFFE2E2E6),
    surface = Color(0xFF111318),
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = Color(0xFF44474F),
    onSurfaceVariant = Color(0xFFC4C6D0),
    outline = Color(0xFF8E9099),
    surfaceContainerLowest = Color(0xFF0C0E13),
    surfaceContainerLow = Color(0xFF1A1C20),
    surfaceContainer = Color(0xFF1E2025),
    surfaceContainerHigh = Color(0xFF282A2F),
    surfaceContainerHighest = Color(0xFF33353A),
)

private val ExpressiveShapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(22.dp),
    large = RoundedCornerShape(28.dp),
    extraLarge = RoundedCornerShape(36.dp),
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun UniApp(
    state: UniUiState,
    actions: UniActions,
) {
    val context = LocalContext.current
    val dark = isSystemInDarkTheme()
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && dark -> dynamicDarkColorScheme(context)
        dynamicColor && !dark -> dynamicLightColorScheme(context)
        dark -> ExpressiveDarkColorScheme
        else -> ExpressiveLightColorScheme
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var aboutVisible by rememberSaveable { mutableStateOf(false) }
    var profileSheetOpen by rememberSaveable { mutableStateOf(false) }
    var editingKey by rememberSaveable { mutableStateOf<String?>(null) }

    MaterialExpressiveTheme(
        colorScheme = colorScheme,
        motionScheme = MotionScheme.expressive(),
        shapes = ExpressiveShapes,
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumFlexibleTopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "UniRoot",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineMedium,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.padding(top = 2.dp),
                            ) {
                                Text(
                                    text = if (state.ksuNextMode) "NEXT" else "CLASSIC",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                )
                            }
                        }
                    },
                    subtitle = {
                        Text(
                            text = if (state.newMethod) {
                                "DirtyFrag (CVE-2026-43284) · Fast Root"
                            } else {
                                state.selectedProfileName?.let { "Profile Exploit · $it" } ?: "Profile Exploit"
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        FilledTonalIconButton(
                            onClick = actions::onToggleAdvanced,
                            shapes = IconButtonDefaults.shapes(),
                            colors = if (state.advancedVisible) {
                                IconButtonDefaults.filledTonalIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                )
                            } else {
                                IconButtonDefaults.filledTonalIconButtonColors()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Tune,
                                contentDescription = stringResource(R.string.action_advanced),
                            )
                        }
                        SettingsMenu(
                            advancedVisible = state.advancedVisible,
                            onToggleAdvanced = actions::onToggleAdvanced,
                            onShowAbout = { aboutVisible = true },
                        )
                    },
                )
            },
            bottomBar = {
                ShortNavigationBar(
                    arrangement = ShortNavigationBarArrangement.EqualWeight,
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    ShortNavigationBarItem(
                        selected = state.tab == UniTab.UNIROOT,
                        onClick = { actions.onTabSelected(UniTab.UNIROOT) },
                        icon = {
                            Icon(
                                imageVector = if (state.rooted) Icons.Rounded.Security else Icons.Rounded.Bolt,
                                contentDescription = stringResource(UniTab.UNIROOT.labelRes),
                            )
                        },
                        label = { Text(text = stringResource(UniTab.UNIROOT.labelRes)) },
                    )
                    ShortNavigationBarItem(
                        selected = state.tab == UniTab.LOGS,
                        onClick = { actions.onTabSelected(UniTab.LOGS) },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Terminal,
                                contentDescription = stringResource(UniTab.LOGS.labelRes),
                            )
                        },
                        label = { Text(text = stringResource(UniTab.LOGS.labelRes)) },
                    )
                }
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    when (state.tab) {
                        UniTab.UNIROOT -> {
                            val content: @Composable (Modifier) -> Unit = { modifier ->
                                UniRootContent(
                                    state = state,
                                    actions = actions,
                                    onOpenProfileSheet = { profileSheetOpen = true },
                                    onAddProfile = { editingKey = "" },
                                    modifier = modifier,
                                )
                            }
                            if (maxWidth < 768.dp) {
                                content(Modifier.fillMaxSize())
                            } else {
                                Row(modifier = Modifier.fillMaxSize()) {
                                    content(Modifier.fillMaxHeight().weight(1f))
                                }
                            }
                        }
                        UniTab.LOGS -> LogsPage(
                            state = state,
                            actions = actions,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }

            UniExecutionSheet(state = state, actions = actions)
            UniKsudUpdateSheet(state = state, actions = actions)
            UniAboutDialog(show = aboutVisible, onDismissRequest = { aboutVisible = false })

            ProfileManagerSheet(
                show = profileSheetOpen,
                profiles = state.profiles,
                onEdit = { profile -> profileSheetOpen = false; editingKey = profile.name },
                onNew = { profileSheetOpen = false; editingKey = "" },
                onDelete = { actions.onProfileDelete(it.name) },
                onDismiss = { profileSheetOpen = false },
            )

            if (editingKey != null) {
                val editing = state.profiles.firstOrNull { it.name == editingKey }
                ProfileEditDialog(
                    profile = editing,
                    onDismiss = { editingKey = null },
                    onSave = { profile, originalName ->
                        actions.onProfileSave(profile, originalName)
                        editingKey = null
                    },
                )
            }

            if (state.instantRootOfferVisible) {
                AlertDialog(
                    onDismissRequest = actions::onInstantRootNotNow,
                    icon = {
                        Surface(
                            shape = MaterialShapes.Sunny.toShape(),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(52.dp),
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Rounded.Bolt,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(28.dp),
                                )
                            }
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(R.string.instant_root_offer_title),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.instant_root_offer_body),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = actions::onInstantRootEnable,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_enable))
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = actions::onInstantRootNotNow,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_not_now))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }

            if (state.keepInstantRootVisible) {
                AlertDialog(
                    onDismissRequest = { actions.onKeepInstantRoot(true) },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Security,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp),
                        )
                    },
                    title = { Text(stringResource(R.string.instant_root_keep_title)) },
                    text = { Text(stringResource(R.string.instant_root_keep_body)) },
                    confirmButton = {
                        Button(
                            onClick = { actions.onKeepInstantRoot(true) },
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_keep_yes))
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = { actions.onKeepInstantRoot(false) },
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_keep_no))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }

            if (state.instantRootInstallVisible) {
                AlertDialog(
                    onDismissRequest = actions::onInstantRootInstallLater,
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.CloudDownload,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp),
                        )
                    },
                    title = { Text(stringResource(R.string.instant_root_install_title)) },
                    text = { Text(stringResource(R.string.instant_root_install_body)) },
                    confirmButton = {
                        Button(
                            onClick = actions::onInstantRootInstallNow,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_install_now))
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = actions::onInstantRootInstallLater,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_install_later))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }

            if (state.instantRootLogVisible) {
                AlertDialog(
                    onDismissRequest = actions::onCloseInstantRootLog,
                    icon = { Icon(Icons.Rounded.Terminal, null) },
                    title = { Text(stringResource(R.string.instant_root_log)) },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 380.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF0F172A))
                                .padding(12.dp)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            SelectionContainer {
                                Text(
                                    text = state.instantRootLogContent,
                                    color = Color(0xFFE2E8F0),
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    lineHeight = 15.sp,
                                )
                            }
                        }
                    },
                    confirmButton = {
                        FilledTonalButton(
                            onClick = actions::onCloseInstantRootLog,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.action_close))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }

            if (state.superuserNeededVisible) {
                AlertDialog(
                    onDismissRequest = actions::onDismissSuperuserNeeded,
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Security,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(36.dp),
                        )
                    },
                    title = { Text(stringResource(R.string.superuser_needed_title)) },
                    text = { Text(stringResource(R.string.superuser_needed_body)) },
                    confirmButton = {
                        Button(
                            onClick = actions::onOpenKsuManager,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.superuser_needed_open))
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = actions::onDismissSuperuserNeeded,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.cancel))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }

            if (state.injectConfirmVisible) {
                AlertDialog(
                    onDismissRequest = actions::onInjectCancel,
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Bolt,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp),
                        )
                    },
                    title = { Text(stringResource(R.string.instant_root_inject_title)) },
                    text = { Text(stringResource(R.string.instant_root_inject_body)) },
                    confirmButton = {
                        Button(
                            onClick = actions::onInjectContinue,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.instant_root_inject_continue))
                        }
                    },
                    dismissButton = {
                        OutlinedButton(
                            onClick = actions::onInjectCancel,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.cancel))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }

            state.logViewerFile?.let { entry ->
                AlertDialog(
                    onDismissRequest = actions::onRunLogViewerClose,
                    icon = { Icon(Icons.Rounded.Terminal, null) },
                    title = { Text(text = entry.file.name, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 380.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF0F172A))
                                .padding(12.dp)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            SelectionContainer {
                                Text(
                                    text = state.logViewerContent,
                                    color = Color(0xFFE2E8F0),
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    lineHeight = 15.sp,
                                )
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = { actions.onRunLogShare(entry) },
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Icon(Icons.Rounded.Share, null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(stringResource(R.string.log_share))
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = actions::onRunLogViewerClose,
                            shapes = ButtonDefaults.shapes(),
                        ) {
                            Text(stringResource(R.string.action_close))
                        }
                    },
                    shape = RoundedCornerShape(28.dp),
                )
            }
        }
    }
}

// ---------------------------------------------------------------------
// Settings Dropdown Menu
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsMenu(
    advancedVisible: Boolean,
    onToggleAdvanced: () -> Unit,
    onShowAbout: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(
            onClick = { expanded = true },
            shapes = IconButtonDefaults.shapes(),
        ) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = stringResource(R.string.action_advanced),
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(20.dp),
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.advanced_settings)) },
                leadingIcon = { Icon(Icons.Rounded.Tune, null) },
                trailingIcon = {
                    if (advancedVisible) {
                        Icon(Icons.Rounded.Check, null, tint = MaterialTheme.colorScheme.primary)
                    }
                },
                onClick = {
                    expanded = false
                    onToggleAdvanced()
                },
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            DropdownMenuItem(
                text = { Text(stringResource(R.string.about)) },
                leadingIcon = { Icon(Icons.Rounded.Info, null) },
                onClick = {
                    expanded = false
                    onShowAbout()
                },
            )
        }
    }
}

// ---------------------------------------------------------------------
// About Dialog
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun UniAboutDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
) {
    if (!show) return
    val uriHandler = LocalUriHandler.current
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = {
            Surface(
                shape = MaterialShapes.Cookie12Sided.toShape(),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(64.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = stringResource(R.string.app_name),
                        modifier = Modifier.size(52.dp),
                    )
                }
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.padding(top = 6.dp),
                ) {
                    Text(
                        text = stringResource(R.string.made_by),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                    )
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp),
                ) {
                    Text(
                        text = stringResource(R.string.view_source) + " ",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = AnnotatedString(
                            text = "GhostLock",
                            spanStyle = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                            ),
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable { uriHandler.openUri("https://github.com/YuKongA/ghostlock-app") },
                    )
                }
                Text(
                    text = stringResource(R.string.opensource_info),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 10.dp),
                )
                Text(
                    text = stringResource(R.string.disclaimer),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismissRequest,
                shapes = ButtonDefaults.shapes(),
            ) {
                Text(stringResource(R.string.action_close))
            }
        },
        shape = RoundedCornerShape(28.dp),
    )
}

// ---------------------------------------------------------------------
// Execution & Ksud Updater Sheets
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun UniKsudUpdateSheet(
    state: UniUiState,
    actions: UniActions,
) {
    if (!state.ksudUpdateSheetVisible) return
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { !state.ksudUpdateRunning })
    ModalBottomSheet(
        onDismissRequest = actions::onCloseKsudUpdateSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = if (state.ksuNextMode) "Update ksud Next" else "Update ksud classic",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                IconButton(
                    onClick = actions::onCloseKsudUpdateSheet,
                    enabled = !state.ksudUpdateRunning,
                    shapes = IconButtonDefaults.shapes(),
                ) {
                    Icon(Icons.Rounded.Close, contentDescription = stringResource(R.string.action_close))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.ksudUpdateRunning) {
                ContainedLoadingIndicator(
                    modifier = Modifier.size(56.dp),
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                )
            } else {
                Surface(
                    shape = MaterialShapes.Sunny.toShape(),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(56.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(28.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.ksudUpdateStage.ifBlank { "Preparing…" },
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(16.dp))

            val barAnim by animateFloatAsState(
                targetValue = state.ksudUpdateProgress.coerceIn(0, 100) / 100f,
                animationSpec = tween(durationMillis = 350),
                label = "ksudUpdateBar",
            )

            LinearWavyProgressIndicator(
                progress = { barAnim },
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${state.ksudUpdateProgress}%",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            if (state.ksudUpdateLog.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF0F172A))
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    SelectionContainer {
                        Text(
                            text = state.ksudUpdateLog,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFFCBD5E1),
                            lineHeight = 15.sp,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun UniExecutionSheet(
    state: UniUiState,
    actions: UniActions,
) {
    if (!state.executionSheetVisible) return
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { state.executionSheetDismissible })
    ModalBottomSheet(
        onDismissRequest = actions::onCloseExecutionSheet,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = actions::onCopyLogs,
                        shapes = IconButtonDefaults.shapes(),
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ContentCopy,
                            contentDescription = stringResource(R.string.action_copy),
                        )
                    }
                    Text(
                        text = stringResource(R.string.log_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp),
                    )
                }
                IconButton(
                    enabled = state.executionSheetDismissible,
                    onClick = actions::onCloseExecutionSheet,
                    shapes = IconButtonDefaults.shapes(),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(R.string.action_close),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.newMethod) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (state.running) {
                        ContainedLoadingIndicator(
                            modifier = Modifier.size(56.dp),
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                        )
                    } else {
                        Surface(
                            shape = MaterialShapes.Sunny.toShape(),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(56.dp),
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = if (state.rooted) Icons.Rounded.Check else Icons.Rounded.Bolt,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(28.dp),
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = state.newMethodStage.ifBlank { "Preparing…" },
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val barAnim by animateFloatAsState(
                        targetValue = state.newMethodProgress.coerceIn(0, 100) / 100f,
                        animationSpec = tween(durationMillis = 350),
                        label = "newMethodBar",
                    )

                    LinearWavyProgressIndicator(
                        progress = { barAnim },
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${state.newMethodProgress}%",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                LogPanel(
                    lines = state.logLines,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 240.dp, max = 460.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ---------------------------------------------------------------------
// UniRoot Content (Home tab)
// ---------------------------------------------------------------------

@Composable
private fun UniRootContent(
    state: UniUiState,
    actions: UniActions,
    onOpenProfileSheet: () -> Unit,
    onAddProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .imePadding(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(key = "controls") {
            ControlPanel(
                state = state,
                actions = actions,
                onOpenProfileSheet = onOpenProfileSheet,
                onAddProfile = onAddProfile,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item(key = "footer") {
            Text(
                text = stringResource(R.string.made_by),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ControlPanel(
    state: UniUiState,
    actions: UniActions,
    onOpenProfileSheet: () -> Unit,
    onAddProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ActivationStatusCard(
            rooted = state.rooted,
            supported = state.kernelSupported,
            modifier = Modifier.fillMaxWidth(),
            liveRootState = state.rootStateRow,
            liveRootDetail = state.rootDetailRow,
            selinux = state.selinuxRow,
        )

        DeviceInfoCard(
            deviceName = state.deviceName,
            socName = state.socName,
            kernelRelease = state.kernelRelease,
            modifier = Modifier.fillMaxWidth(),
            infoRows = state.deviceInfoRows,
        )

        // Expressive Connected Button Group: Old Method vs New Method
        MethodSwitch(
            newMethod = state.newMethod,
            onSelect = actions::onRootMethodChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        // Expressive Connected Button Group: KernelSU vs KernelSU Next
        FlavorSwitch(
            next = state.ksuNextMode,
            onSelect = actions::onKsuFlavorChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        if (!state.newMethod) {
            // Old method profile selector
            if (state.profiles.isNotEmpty()) {
                ExpressiveSpinnerCard(
                    title = stringResource(R.string.profile_label),
                    items = state.profiles.map { it.name },
                    selectedIndex = state.profiles.indexOfFirst { it.name == state.selectedProfileName }.coerceAtLeast(0),
                    onSelect = actions::onProfileSelected,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            OutlinedButton(
                onClick = onAddProfile,
                shapes = ButtonDefaults.shapes(),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(Icons.Rounded.Layers, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.action_add_profile))
            }

            RunButton(
                rooted = state.rooted,
                running = state.running,
                supported = state.kernelSupported,
                onRun = actions::onRun,
                modifier = Modifier.fillMaxWidth(),
            )

            ExpressiveSwitchCard(
                title = stringResource(R.string.shizuku_label),
                summary = stringResource(R.string.shizuku_summary),
                checked = state.shizukuEnabled,
                onCheckedChange = actions::onShizukuChanged,
                modifier = Modifier.fillMaxWidth(),
            )
        } else {
            // New Method actions
            FilledTonalButton(
                onClick = actions::onUpdateKsud,
                enabled = !state.ksudUpdateRunning,
                shapes = ButtonDefaults.shapesFor(ButtonDefaults.MediumContainerHeight),
                contentPadding = ButtonDefaults.contentPaddingFor(ButtonDefaults.MediumContainerHeight),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = ButtonDefaults.MediumContainerHeight),
            ) {
                if (state.ksudUpdateRunning) {
                    LoadingIndicator(modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(10.dp))
                    Text("Updating ksud…")
                } else {
                    Icon(Icons.Rounded.CloudDownload, null, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(10.dp))
                    Text(if (state.ksuNextMode) "Update ksud Next" else "Update ksud classic")
                }
            }

            RunButton(
                rooted = state.rooted,
                running = state.running,
                supported = true,
                onRun = actions::onRun,
                modifier = Modifier.fillMaxWidth(),
            )

            if (state.ksuNextMode && state.ksudNextProfiles.isNotEmpty()) {
                ExpressiveSpinnerCard(
                    title = stringResource(R.string.ksud_next_profile_label),
                    items = state.ksudNextProfiles.map { it.label },
                    selectedIndex = state.ksudNextProfiles.indexOfFirst { it.id == state.ksudNextProfileId }.coerceAtLeast(0),
                    onSelect = { index -> actions.onKsudNextProfileChanged(state.ksudNextProfiles[index].id) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            if (!state.ksuNextMode && state.ksudClassicProfiles.isNotEmpty()) {
                ExpressiveSpinnerCard(
                    title = stringResource(R.string.ksud_next_profile_label),
                    items = state.ksudClassicProfiles.map { it.label },
                    selectedIndex = state.ksudClassicProfiles.indexOfFirst { it.id == state.ksudClassicProfileId }.coerceAtLeast(0),
                    onSelect = { index -> actions.onKsudClassicProfileChanged(state.ksudClassicProfiles[index].id) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
                    ExpressiveSwitchRow(
                        title = stringResource(R.string.new_method_boot_title),
                        summary = stringResource(R.string.new_method_boot_summary),
                        checked = state.newMethodBootEnabled,
                        onCheckedChange = actions::onNewMethodBootChanged,
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    ExpressiveSwitchRow(
                        title = stringResource(R.string.new_method_soft_reboot_title),
                        summary = stringResource(R.string.new_method_soft_reboot_summary),
                        checked = state.newMethodAutoSoftReboot,
                        onCheckedChange = actions::onNewMethodSoftRebootChanged,
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = state.advancedVisible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            if (state.newMethod) {
                NewMethodOptions(state = state, actions = actions)
            } else {
                AdvancedOptions(
                    state = state,
                    actions = actions,
                    onOpenProfileSheet = onOpenProfileSheet,
                )
            }
        }
    }
}

// ---------------------------------------------------------------------
// Hero Status Card
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ActivationStatusCard(
    rooted: Boolean,
    supported: Boolean,
    modifier: Modifier = Modifier,
    liveRootState: String = "",
    liveRootDetail: String = "",
    selinux: String = "",
) {
    val rootedLive = liveRootState.startsWith("Rooted") || (liveRootState == "Armed" && rooted)
    val failedLive = liveRootState.startsWith("Chain failed")
    val positive = rooted || rootedLive

    val containerColor = when {
        positive -> if (isSystemInDarkTheme()) Color(0xFF143823) else Color(0xFFD4F8DE)
        failedLive -> if (isSystemInDarkTheme()) Color(0xFF3E1A18) else Color(0xFFFFDAD6)
        supported -> if (isSystemInDarkTheme()) Color(0xFF1B2E3D) else Color(0xFFD7EAF8)
        else -> if (isSystemInDarkTheme()) Color(0xFF382315) else Color(0xFFFFE8D6)
    }

    val contentColor = when {
        positive -> if (isSystemInDarkTheme()) Color(0xFF94F7B2) else Color(0xFF0D632C)
        failedLive -> if (isSystemInDarkTheme()) Color(0xFFFFB4AB) else Color(0xFF93000A)
        supported -> if (isSystemInDarkTheme()) Color(0xFFA1D1FF) else Color(0xFF004D7A)
        else -> if (isSystemInDarkTheme()) Color(0xFFFFD199) else Color(0xFF8B4F00)
    }

    val statusIcon = when {
        positive -> Icons.Rounded.Verified
        failedLive -> Icons.Rounded.ErrorOutline
        supported -> Icons.Rounded.CheckCircleOutline
        else -> Icons.Rounded.RemoveCircleOutline
    }

    val shapeBadge = when {
        positive -> MaterialShapes.Sunny.toShape()
        failedLive -> MaterialShapes.Cookie9Sided.toShape()
        supported -> MaterialShapes.SoftBurst.toShape()
        else -> MaterialShapes.Cookie4Sided.toShape()
    }

    val title = when {
        rootedLive || rooted -> stringResource(R.string.status_rooted)
        failedLive -> stringResource(R.string.root_chain_failed)
        supported -> stringResource(R.string.kernel_supported)
        else -> stringResource(R.string.kernel_unsupported)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = CircleShape,
                    color = contentColor.copy(alpha = 0.16f),
                ) {
                    Text(
                        text = if (positive) "ACTIVE" else if (supported) "READY" else "UNKNOWN",
                        color = contentColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = contentColor,
                )
                if (liveRootDetail.isNotBlank()) {
                    Text(
                        text = liveRootDetail,
                        style = MaterialTheme.typography.bodySmall,
                        color = contentColor.copy(alpha = 0.82f),
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }
                if (selinux.isNotBlank()) {
                    Text(
                        text = "SELinux: $selinux",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = contentColor.copy(alpha = 0.72f),
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }
            }

            Surface(
                shape = shapeBadge,
                color = contentColor.copy(alpha = 0.18f),
                modifier = Modifier.size(68.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = statusIcon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(36.dp),
                    )
                }
            }
        }
    }
}

// ---------------------------------------------------------------------
// Device Info Card
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun DeviceInfoCard(
    deviceName: String,
    socName: String,
    kernelRelease: String,
    modifier: Modifier = Modifier,
    infoRows: List<Pair<String, String>> = emptyList(),
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = MaterialShapes.Cookie6Sided.toShape(),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.size(36.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.PhoneAndroid,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Device Specifications",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }

            SelectionContainer {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (infoRows.isEmpty()) {
                        DeviceInfoRowItem("Device", deviceName, Icons.Rounded.Smartphone)
                        DeviceInfoRowItem("SoC", socName, Icons.Rounded.Memory)
                        DeviceInfoRowItem("Kernel", kernelRelease, Icons.Rounded.DeveloperBoard)
                    } else {
                        infoRows.forEach { (k, v) ->
                            val icon = when (k.lowercase()) {
                                "device" -> Icons.Rounded.Smartphone
                                "android" -> Icons.Rounded.Android
                                "soc" -> Icons.Rounded.Memory
                                "kernel" -> Icons.Rounded.DeveloperBoard
                                else -> Icons.Rounded.Info
                            }
                            DeviceInfoRowItem(k, v, icon)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DeviceInfoRowItem(title: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

// ---------------------------------------------------------------------
// Connected Toggle Button Groups (Method & Flavor)
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun MethodSwitch(
    newMethod: Boolean,
    onSelect: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        TonalToggleButton(
            checked = !newMethod,
            onCheckedChange = { onSelect(false) },
            shapes = ButtonGroupDefaults.connectedLeadingButtonShapes(),
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = if (!newMethod) Icons.Rounded.Check else Icons.Rounded.History,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.method_old), maxLines = 1)
        }

        TonalToggleButton(
            checked = newMethod,
            onCheckedChange = { onSelect(true) },
            shapes = ButtonGroupDefaults.connectedTrailingButtonShapes(),
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = if (newMethod) Icons.Rounded.Check else Icons.Rounded.Bolt,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.method_new), maxLines = 1)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun FlavorSwitch(
    next: Boolean,
    onSelect: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        TonalToggleButton(
            checked = !next,
            onCheckedChange = { onSelect(false) },
            shapes = ButtonGroupDefaults.connectedLeadingButtonShapes(),
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = if (!next) Icons.Rounded.Check else Icons.Rounded.Security,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.flavor_kernelsu), maxLines = 1)
        }

        TonalToggleButton(
            checked = next,
            onCheckedChange = { onSelect(true) },
            shapes = ButtonGroupDefaults.connectedTrailingButtonShapes(),
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = if (next) Icons.Rounded.Check else Icons.Rounded.AutoAwesome,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.flavor_ksunext), maxLines = 1)
        }
    }
}

// ---------------------------------------------------------------------
// Primary Action Button
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun RunButton(
    rooted: Boolean,
    running: Boolean,
    supported: Boolean,
    onRun: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = stringResource(
        when {
            running -> R.string.action_running
            rooted -> R.string.action_already_root
            else -> R.string.action_run
        }
    )

    Button(
        onClick = onRun,
        enabled = !running && !rooted && supported,
        shapes = ButtonDefaults.shapesFor(ButtonDefaults.LargeContainerHeight),
        contentPadding = ButtonDefaults.contentPaddingFor(ButtonDefaults.LargeContainerHeight),
        modifier = modifier.heightIn(min = ButtonDefaults.LargeContainerHeight),
    ) {
        if (running) {
            LoadingIndicator(modifier = Modifier.size(24.dp))
            Spacer(Modifier.width(10.dp))
        } else if (rooted) {
            Icon(Icons.Rounded.CheckCircle, null, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(8.dp))
        } else {
            Icon(Icons.Rounded.Bolt, null, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

// ---------------------------------------------------------------------
// Expressive Preference Components
// ---------------------------------------------------------------------

@Composable
private fun ExpressiveSpinnerCard(
    title: String,
    items: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val currentLabel = items.getOrNull(selectedIndex) ?: ""

    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier.clickable { expanded = true },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = currentLabel,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.size(36.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.ExpandMore,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    shape = RoundedCornerShape(18.dp),
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            trailingIcon = {
                                if (index == selectedIndex) {
                                    Icon(Icons.Rounded.Check, null, tint = MaterialTheme.colorScheme.primary)
                                }
                            },
                            onClick = {
                                expanded = false
                                onSelect(index)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ExpressiveSwitchCard(
    title: String,
    summary: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier,
    ) {
        ExpressiveSwitchRow(
            title = title,
            summary = summary,
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
        )
    }
}

@Composable
private fun ExpressiveSwitchRow(
    title: String,
    summary: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (summary.isNotBlank()) {
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            thumbContent = {
                Icon(
                    imageVector = if (checked) Icons.Rounded.Check else Icons.Rounded.Close,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            },
        )
    }
}

// ---------------------------------------------------------------------
// Advanced Options
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun NewMethodOptions(
    state: UniUiState,
    actions: UniActions,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.new_method_advanced_info),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp),
            )
        }

        state.runLogs.firstOrNull()?.let { latest ->
            OutlinedButton(
                onClick = { actions.onRunLogOpen(latest) },
                shapes = ButtonDefaults.shapes(),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(Icons.Rounded.Visibility, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.see_last_log))
            }
        }

        val dynamicNext = state.ksudNextProfiles.filter { it.dynamic }
        if (dynamicNext.isNotEmpty()) {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Downloaded ksud Next profiles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(8.dp))
                    for (p in dynamicNext) {
                        ProfileDeleteRow(p.label, p.note) { actions.onDeleteKsudProfile(p.id, classic = false) }
                    }
                }
            }
        }

        val dynamicClassic = state.ksudClassicProfiles.filter { it.dynamic }
        if (dynamicClassic.isNotEmpty()) {
            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Downloaded ksud classic profiles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(8.dp))
                    for (p in dynamicClassic) {
                        ProfileDeleteRow(p.label, p.note) { actions.onDeleteKsudProfile(p.id, classic = true) }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileDeleteRow(label: String, note: String, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            if (note.isNotBlank()) {
                Text(text = note, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        IconButton(onClick = onDelete) {
            Icon(Icons.Rounded.DeleteOutline, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun AdvancedOptions(
    state: UniUiState,
    actions: UniActions,
    onOpenProfileSheet: () -> Unit,
) {
    var uninstallConfirm by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Root My Galaxy",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = state.rmgStatus.ifEmpty { stringResource(R.string.rmg_idle) },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp),
                )
                Spacer(Modifier.height(8.dp))
                FilledTonalButton(
                    onClick = actions::onCheckRmg,
                    shapes = ButtonDefaults.shapes(),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(Icons.Rounded.CloudDownload, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(stringResource(R.string.rmg_check))
                }
            }
        }

        OutlinedButton(
            onClick = onOpenProfileSheet,
            shapes = ButtonDefaults.shapes(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Rounded.Edit, null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.edit_profiles))
        }

        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ExpressiveSwitchRow(
                    title = stringResource(R.string.use_latest_ksu),
                    summary = stringResource(R.string.use_latest_ksu_warning),
                    checked = state.useLatestKsu,
                    onCheckedChange = actions::onUseLatestKsuChanged,
                )
                if (state.useLatestKsu) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = if (state.latestKsuTag.isEmpty()) {
                            stringResource(R.string.use_latest_not_downloaded)
                        } else {
                            stringResource(R.string.use_latest_downloaded, state.latestKsuTag)
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.height(8.dp))
                    FilledTonalButton(
                        onClick = actions::onDownloadLatestKsu,
                        shapes = ButtonDefaults.shapes(),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.download_latest_ksu))
                    }
                }
            }
        }

        ExpressiveSwitchCard(
            title = stringResource(R.string.auto_root_on_boot),
            summary = stringResource(R.string.auto_root_on_boot_summary),
            checked = state.autoRootOnBoot,
            onCheckedChange = actions::onAutoRootChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ExpressiveSwitchRow(
                    title = stringResource(R.string.instant_root_title),
                    summary = when {
                        state.instantRootEnabled -> stringResource(R.string.instant_root_enabled_summary)
                        state.instantRootInstalled -> stringResource(R.string.instant_root_off_summary)
                        else -> stringResource(R.string.instant_root_not_installed)
                    },
                    checked = state.instantRootEnabled,
                    onCheckedChange = actions::onInstantRootEnabledChanged,
                )
                Text(
                    text = if (state.instantRootInstalled) {
                        stringResource(R.string.instant_root_status_installed)
                    } else {
                        stringResource(R.string.instant_root_status_missing)
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp),
                )
                Spacer(Modifier.height(8.dp))
                FilledTonalButton(
                    onClick = actions::onOpenInstantRootLog,
                    shapes = ButtonDefaults.shapes(),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(stringResource(R.string.instant_root_log))
                }
                if (state.instantRootInstalled) {
                    Spacer(Modifier.height(4.dp))
                    FilledTonalButton(
                        onClick = actions::onOpenHelper,
                        shapes = ButtonDefaults.shapes(),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.instant_root_open_helper))
                    }
                    Spacer(Modifier.height(4.dp))
                    FilledTonalButton(
                        onClick = actions::onInstantRootInstallNow,
                        shapes = ButtonDefaults.shapes(),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.instant_root_update_helper))
                    }
                    Spacer(Modifier.height(4.dp))
                    OutlinedButton(
                        onClick = { uninstallConfirm = true },
                        shapes = ButtonDefaults.shapes(),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.instant_root_uninstall_helper))
                    }
                }
            }
        }

        OutlinedButton(
            onClick = actions::onResetProfiles,
            shapes = ButtonDefaults.shapes(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Rounded.RestartAlt, null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(stringResource(R.string.reset_profiles))
        }

        state.runLogs.firstOrNull()?.let { latest ->
            OutlinedButton(
                onClick = { actions.onRunLogOpen(latest) },
                shapes = ButtonDefaults.shapes(),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(Icons.Rounded.Visibility, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.see_last_log))
            }
        }
    }

    if (uninstallConfirm) {
        AlertDialog(
            onDismissRequest = { uninstallConfirm = false },
            icon = { Icon(Icons.Rounded.DeleteOutline, null, tint = MaterialTheme.colorScheme.error) },
            title = { Text(stringResource(R.string.instant_root_uninstall_helper)) },
            text = { Text(stringResource(R.string.instant_root_uninstall_confirm)) },
            confirmButton = {
                Button(
                    onClick = {
                        uninstallConfirm = false
                        actions.onUninstallHelper()
                    },
                    shapes = ButtonDefaults.shapes(),
                ) {
                    Text(stringResource(R.string.instant_root_uninstall))
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { uninstallConfirm = false },
                    shapes = ButtonDefaults.shapes(),
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
            shape = RoundedCornerShape(28.dp),
        )
    }
}

// ---------------------------------------------------------------------
// Logs Page
// ---------------------------------------------------------------------

@Composable
private fun LogsPage(
    state: UniUiState,
    actions: UniActions,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .imePadding(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(key = "current-log") {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.log_title),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(8.dp))
                    if (state.logLines.isEmpty()) {
                        Text(
                            text = stringResource(R.string.logs_empty),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    } else {
                        LogPanel(
                            lines = state.logLines,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 380.dp),
                        )
                    }
                }
            }
        }

        if (state.runLogs.isNotEmpty()) {
            item(key = "history-title") {
                Text(
                    text = stringResource(R.string.run_history),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 4.dp, top = 8.dp),
                )
            }
            items(state.runLogs, key = { it.file.absolutePath }) { entry ->
                RunLogBox(
                    entry = entry,
                    onOpen = { actions.onRunLogOpen(entry) },
                    onShare = { actions.onRunLogShare(entry) },
                    onDelete = { actions.onRunLogDelete(entry) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun RunLogBox(
    entry: RunLogFile,
    onOpen: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val time = remember(entry.timeMillis) {
        java.text.SimpleDateFormat("MMM d, yyyy · HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date(entry.timeMillis))
    }
    val (statusColor, statusLabel) = when (entry.status.lowercase()) {
        "success", "completed" -> Color(0xFF10B981) to "Completed"
        "failed" -> Color(0xFFEF4444) to "Failed"
        "crash" -> Color(0xFFF59E0B) to "Crash"
        "reboot-required" -> Color(0xFF3B82F6) to "Reboot required"
        "failed-interrupted" -> Color(0xFFF97316) to "Failed (interrupted)"
        else -> Color(0xFF94A3B8) to entry.status
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = statusLabel,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = statusColor,
                    )
                }
                Surface(
                    shape = CircleShape,
                    color = statusColor.copy(alpha = 0.16f),
                    modifier = Modifier.size(36.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = if (entry.status.equals("success", ignoreCase = true)) Icons.Rounded.Check else Icons.Rounded.Terminal,
                            contentDescription = null,
                            tint = statusColor,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FilledTonalButton(
                    onClick = onOpen,
                    shapes = ButtonDefaults.shapes(),
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(Icons.Rounded.Visibility, null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(stringResource(R.string.log_view))
                }
                OutlinedButton(
                    onClick = onShare,
                    shapes = ButtonDefaults.shapes(),
                    modifier = Modifier.weight(1f),
                ) {
                    Icon(Icons.Rounded.Share, null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(stringResource(R.string.log_share))
                }
                IconButton(
                    onClick = onDelete,
                    shapes = IconButtonDefaults.shapes(),
                ) {
                    Icon(Icons.Rounded.DeleteOutline, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

// ---------------------------------------------------------------------
// Log Panel & Formatting
// ---------------------------------------------------------------------

@Composable
private fun LogPanel(
    lines: List<String>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    LaunchedEffect(lines.size) {
        if (lines.isNotEmpty()) listState.scrollToItem(lines.lastIndex)
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF0B1220))
            .padding(12.dp),
    ) {
        SelectionContainer {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(lines) { line ->
                    Text(
                        text = line.trimEnd('\r', '\n'),
                        color = toneColor(formatLogTone(line)),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                    )
                }
            }
        }
    }
}

enum class LogTone { Default, Error, Success, Warning, Progress }

private val ansiRegex = Regex("\\u001B\\[[;\\d]*m")
private val leadingTags = Regex("^(\\[[^]]+\\]\\s*)+")

fun formatLogTone(line: String): LogTone {
    val text = stripAnsi(line)
    val marker = text.getOrNull(1).takeIf { text.startsWith('[') && text.getOrNull(2) == ']' }
    val message = text.replace(leadingTags, "").removePrefix("=== ")
    return when {
        isWriteRound(message) && (marker == '-' || marker == '!') -> LogTone.Error
        isWriteRound(message) -> LogTone.Progress
        marker == '+' -> LogTone.Success
        marker == '-' || marker == '!' -> LogTone.Error
        marker == '*' -> LogTone.Warning
        text.startsWith("error", ignoreCase = true) -> LogTone.Error
        text.startsWith("warning", ignoreCase = true) -> LogTone.Warning
        else -> LogTone.Default
    }
}

private fun isWriteRound(message: String): Boolean =
    listOf("W1", "W2", "W3", "Write 1").any(message::startsWith)

private fun stripAnsi(value: String): String = value.replace(ansiRegex, "")

private fun toneColor(tone: LogTone): Color = when (tone) {
    LogTone.Error -> Color(0xFFFF6B6B)
    LogTone.Success -> Color(0xFF5FD68A)
    LogTone.Warning -> Color(0xFFFFC94D)
    LogTone.Progress -> Color(0xFF60A5FA)
    LogTone.Default -> Color(0xFFD1D5DB)
}

// ---------------------------------------------------------------------
// Profile Manager Sheet & Edit Dialog
// ---------------------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ProfileManagerSheet(
    show: Boolean,
    profiles: List<DeviceProfile>,
    onEdit: (DeviceProfile) -> Unit,
    onNew: () -> Unit,
    onDelete: (DeviceProfile) -> Unit,
    onDismiss: () -> Unit,
) {
    if (!show) return
    var pendingDelete by remember { mutableStateOf<DeviceProfile?>(null) }
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.edit_profiles),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                IconButton(onClick = onDismiss, shapes = IconButtonDefaults.shapes()) {
                    Icon(Icons.Rounded.Close, contentDescription = stringResource(R.string.action_close))
                }
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 440.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(profiles, key = { it.name }) { profile ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = profile.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = fileSummary(profile),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 2.dp),
                            )
                            Spacer(Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                FilledTonalButton(
                                    onClick = { onEdit(profile) },
                                    shapes = ButtonDefaults.shapes(),
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Icon(Icons.Rounded.Edit, null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text(stringResource(R.string.action_edit))
                                }
                                OutlinedButton(
                                    onClick = { pendingDelete = profile },
                                    shapes = ButtonDefaults.shapes(),
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Icon(Icons.Rounded.DeleteOutline, null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(6.dp))
                                    Text(stringResource(R.string.action_delete))
                                }
                            }
                        }
                    }
                }

                item(key = "new") {
                    Button(
                        onClick = onNew,
                        shapes = ButtonDefaults.shapes(),
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    ) {
                        Icon(Icons.Rounded.Layers, null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.new_profile))
                    }
                }
            }
        }
    }

    pendingDelete?.let { victim ->
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            icon = { Icon(Icons.Rounded.DeleteOutline, null, tint = MaterialTheme.colorScheme.error) },
            title = { Text(stringResource(R.string.action_delete)) },
            text = { Text(stringResource(R.string.delete_profile_confirm, victim.name)) },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete(victim)
                        pendingDelete = null
                    },
                    shapes = ButtonDefaults.shapes(),
                ) {
                    Text(stringResource(R.string.action_delete))
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { pendingDelete = null },
                    shapes = ButtonDefaults.shapes(),
                ) {
                    Text(stringResource(R.string.cancel))
                }
            },
            shape = RoundedCornerShape(28.dp),
        )
    }
}

private fun fileSummary(profile: DeviceProfile): String {
    fun n(path: String) = File(path).name.ifEmpty { "?" }
    val parts = mutableListOf("SO: ${n(profile.pathSo)}", "KO: ${n(profile.pathKo)}", "KSUD: ${n(profile.pathKsud)}")
    if (!profile.pathCveNormal.isNullOrEmpty()) parts.add("CVE: ${n(profile.pathCveNormal)}")
    if (!profile.pathCveRoot.isNullOrEmpty()) parts.add("ROOT: ${n(profile.pathCveRoot!!)}")
    if (profile.useShizuku) parts.add("Shizuku")
    return parts.joinToString("  ·  ")
}

private enum class FileRole { SO, KO, KSUD, CVE_NORMAL, CVE_ROOT }

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ProfileEditDialog(
    profile: DeviceProfile?,
    onDismiss: () -> Unit,
    onSave: (DeviceProfile, String?) -> Unit,
) {
    val context = LocalContext.current
    val stateKey = profile?.name ?: "__new__"
    var name by remember(stateKey) { mutableStateOf(profile?.name ?: "") }
    var kaslr by remember(stateKey) { mutableStateOf(profile?.kaslrOffset ?: "") }
    var deviceType by remember(stateKey) { mutableStateOf(profile?.deviceType ?: "samsung") }
    var useShizuku by remember(stateKey) { mutableStateOf(profile?.useShizuku ?: false) }
    var soPath by remember(stateKey) { mutableStateOf(profile?.pathSo ?: "") }
    var koPath by remember(stateKey) { mutableStateOf(profile?.pathKo ?: "") }
    var ksudPath by remember(stateKey) { mutableStateOf(profile?.pathKsud ?: "") }
    var cveNormalPath by remember(stateKey) { mutableStateOf(profile?.pathCveNormal ?: "") }
    var cveRootPath by remember(stateKey) { mutableStateOf(profile?.pathCveRoot ?: "") }
    var pendingRole by remember { mutableStateOf<FileRole?>(null) }

    val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        val role = pendingRole
        pendingRole = null
        if (uri != null && role != null) {
            val dest = copyPickedFile(context, uri)
            if (dest == null) {
                Toast.makeText(context, R.string.pick_failed, Toast.LENGTH_SHORT).show()
            } else {
                when (role) {
                    FileRole.SO -> soPath = dest.absolutePath
                    FileRole.KO -> koPath = dest.absolutePath
                    FileRole.KSUD -> ksudPath = dest.absolutePath
                    FileRole.CVE_NORMAL -> cveNormalPath = dest.absolutePath
                    FileRole.CVE_ROOT -> cveRootPath = dest.absolutePath
                }
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                stringResource(if (profile == null) R.string.new_profile else R.string.edit_profile),
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 440.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.field_name)) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = kaslr,
                    onValueChange = { kaslr = it },
                    label = { Text(stringResource(R.string.field_kaslr)) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
                ) {
                    TonalToggleButton(
                        checked = deviceType == "samsung",
                        onCheckedChange = { deviceType = "samsung" },
                        shapes = ButtonGroupDefaults.connectedLeadingButtonShapes(),
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(stringResource(R.string.field_device_samsung))
                    }
                    TonalToggleButton(
                        checked = deviceType == "oppo",
                        onCheckedChange = { deviceType = "oppo" },
                        shapes = ButtonGroupDefaults.connectedTrailingButtonShapes(),
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(stringResource(R.string.field_device_other))
                    }
                }
                ExpressiveSwitchRow(
                    title = stringResource(R.string.profile_use_shizuku),
                    summary = stringResource(R.string.profile_use_shizuku_summary),
                    checked = useShizuku,
                    onCheckedChange = { useShizuku = it },
                )
                FileField(stringResource(R.string.field_exploit_so), soPath) { pendingRole = FileRole.SO; picker.launch("*/*") }
                FileField(stringResource(R.string.field_ko), koPath) { pendingRole = FileRole.KO; picker.launch("*/*") }
                FileField(stringResource(R.string.field_ksud), ksudPath) { pendingRole = FileRole.KSUD; picker.launch("*/*") }
                FileField(stringResource(R.string.field_cve_normal), cveNormalPath) { pendingRole = FileRole.CVE_NORMAL; picker.launch("*/*") }
                FileField(stringResource(R.string.field_cve_root), cveRootPath) { pendingRole = FileRole.CVE_ROOT; picker.launch("*/*") }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isBlank() || soPath.isBlank() || koPath.isBlank() || ksudPath.isBlank()) {
                        Toast.makeText(context, R.string.profile_missing_fields, Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val updated = DeviceProfile(
                        name = name.trim(),
                        kaslrOffset = kaslr.trim(),
                        pathSo = soPath,
                        pathKo = koPath,
                        pathKsud = ksudPath,
                        deviceType = deviceType,
                        pathCveNormal = cveNormalPath.ifBlank { null },
                        pathCveRoot = cveRootPath.ifBlank { null },
                        flavor = profile?.flavor ?: "kernelsu",
                        useShizuku = useShizuku,
                    )
                    onSave(updated, profile?.name)
                },
                shapes = ButtonDefaults.shapes(),
            ) {
                Text(stringResource(R.string.save))
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss,
                shapes = ButtonDefaults.shapes(),
            ) {
                Text(stringResource(R.string.cancel))
            }
        },
        shape = RoundedCornerShape(28.dp),
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun FileField(
    label: String,
    path: String,
    onPick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Text(
                text = path.substringAfterLast('/').ifEmpty { stringResource(R.string.no_file) },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(Modifier.width(8.dp))
        FilledTonalButton(
            onClick = onPick,
            shapes = ButtonDefaults.shapes(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            modifier = Modifier.heightIn(min = 36.dp),
        ) {
            Text(stringResource(R.string.choose_file), fontSize = 12.sp)
        }
    }
}

private fun copyPickedFile(context: Context, uri: Uri, prefix: String = ""): File? = runCatching {
    val name = context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) cursor.getString(0) else null
    } ?: "file.bin"
    val safe = name.replace(Regex("[^A-Za-z0-9._-]"), "_")
    val dir = File(context.getExternalFilesDir(null), "custom").apply { mkdirs() }
    val dest = File(dir, "${System.currentTimeMillis()}${if (prefix.isNotEmpty()) "-$prefix" else ""}-$safe")
    context.contentResolver.openInputStream(uri)!!.use { input ->
        dest.outputStream().use { output -> input.copyTo(output) }
    }
    dest.setReadable(true, false)
    dest.setExecutable(true, false)
    dest
}.getOrNull()

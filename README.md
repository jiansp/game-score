# 台球计分器 / Billiards Score Tracker

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)
[![Wear OS](https://img.shields.io/badge/Wearable-Wear%20OS-orange.svg)](https://wearos.google.com/)

一款专为智能手表设计的台球计分应用，支持2人或3人游戏模式，提供便捷的分数追踪功能。

A billiards scoring app designed for smartwatches, supporting 2 or 3 player modes with convenient score tracking.

## 功能特性 / Features

### 中文
- 🎯 **多玩家支持**：支持2人或3人游戏模式切换
- 📊 **多种计分方式**：
  - 普胜：获胜者 +4分，失败者 -4分
  - 犯规：犯规者 -1分，受益者 +1分
  - 小金：获胜者 +7分，失败者 -7分
  - 大金：获胜者 +10分，其他所有玩家各 -10分
- 💾 **自动保存**：分数自动保存到本地存储
- ⌚ **手表优化**：专为 Wear OS 设备设计，支持表冠滚动导航
- 🎨 **深色主题**：护眼设计，适合各种光线环境
- 🔄 **快速重置**：一键重置所有分数

### English
- 🎯 **Multi-player Support**: Switch between 2 or 3 player modes
- 📊 **Multiple Scoring Types**:
  - Normal Win: Winner +4 points, Loser -4 points
  - Foul: Fouler -1 point, Beneficiary +1 point
  - Small Win: Winner +7 points, Loser -7 points
  - Big Win: Winner +10 points, All other players -10 points each
- 💾 **Auto-save**: Scores automatically saved to local storage
- ⌚ **Watch Optimized**: Designed for Wear OS devices with crown scroll navigation
- 🎨 **Dark Theme**: Eye-friendly design suitable for various lighting conditions
- 🔄 **Quick Reset**: One-click reset for all scores

## 截图 / Screenshots

![应用界面](screenshots/app-screenshot.png)

## 安装说明 / Installation

### 从源码构建 / Build from Source

#### 前置要求 / Prerequisites

- **JDK 17** (推荐 Temurin 17)
- **Android Studio Hedgehog** 或更高版本
- **Android SDK Platform 36**
- **Gradle 9.4.1** (项目自带 wrapper)

#### 构建步骤 / Build Steps

1. 克隆仓库 / Clone the repository:
```bash
git clone https://github.com/your-username/game-score.git
cd game-score
```

2. 配置 JDK 17 / Configure JDK 17:

**macOS (使用 Homebrew)**:
```bash
brew install openjdk@17
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@17/17.0.19/libexec/openjdk.jdk/Contents/Home
```

**或者手动设置 JAVA_HOME**:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

3. 赋予执行权限 / Grant execute permission:
```bash
chmod +x gradlew
```

4. 构建 Release 版本 / Build Release APK:
```bash
./gradlew assembleRelease
```

生成的 APK 文件位置 / Generated APK location:
```
app/build/outputs/apk/release/app-release.apk
```

5. 构建 Debug 版本 (可选) / Build Debug APK (Optional):
```bash
./gradlew assembleDebug
```

### 使用 Android Studio / Using Android Studio

1. 打开 Android Studio
2. 选择 "Open an existing project"
3. 导航到项目目录并打开
4. 等待 Gradle 同步完成
5. 点击 `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
6. APK 文件将生成在 `app/build/outputs/apk/debug/` 目录

### 直接安装 / Direct Installation

下载最新版本的 APK 文件并安装到您的 Wear OS 设备上。

Download the latest APK file and install it on your Wear OS device.

## 使用说明 / Usage

### 基本操作 / Basic Operations

1. **选择玩家数量** / Select Player Count:
   - 点击顶部的 "2人/3人" 按钮切换模式

2. **记录得分** / Record Scores:
   - 点击对应玩家的按钮记录不同类型的得分
   - 在3人模式下，系统会提示您选择目标玩家

3. **重置分数** / Reset Scores:
   - 点击 "重置" 按钮
   - 确认操作以将所有分数重置为初始值（100分）

4. **滚动页面** / Scroll Page:
   - 使用手表表冠旋转来滚动页面
   - 或在触摸屏上滑动

### 计分规则 / Scoring Rules

| 类型 / Type | 说明 / Description | 分值 / Points |
|------------|-------------------|---------------|
| 普胜 / Normal Win | 普通胜利 / Normal victory | ±4 |
| 犯规 / Foul | 犯规处罚 / Foul penalty | ±1 |
| 小金 / Small Win | 小胜利 / Small victory | ±7 |
| 大金 / Big Win | 大胜利 / Big victory | ±10 |

## 技术栈 / Tech Stack

- **语言 / Language**: Kotlin
- **最低 SDK / Min SDK**: API 28 (Android 9.0)
- **编译 SDK / Compile SDK**: API 36 (Android 15)
- **目标 SDK / Target SDK**: API 36 (Android 15)
- **平台 / Platform**: Wear OS
- **构建工具 / Build Tools**: Gradle 9.4.1 + AGP 9.2.1
- **JDK 版本 / JDK Version**: 17 (Temurin recommended)
- **数据存储 / Data Storage**: SharedPreferences

## 项目结构 / Project Structure

```
app/
├── src/main/
│   ├── java/com/spheign/gamescore/
│   │   └── MainActivity.kt          # 主活动 / Main Activity
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml    # 主布局 / Main Layout
│   │   ├── values/
│   │   │   ├── strings.xml          # 字符串资源 / String Resources
│   │   │   ├── colors.xml           # 颜色资源 / Color Resources
│   │   │   └── themes.xml           # 主题配置 / Theme Configuration
│   │   └── drawable/                # 图片资源 / Image Resources
│   └── AndroidManifest.xml          # 应用清单 / App Manifest
└── build.gradle.kts                 # Gradle 配置 / Gradle Config
```

## 开发说明 / Development Notes

### 构建要求 / Build Requirements

- **JDK**: 17 或更高版本 (推荐 Temurin 17) / JDK 17 or later (Temurin 17 recommended)
- **Android Studio**: Hedgehog 或更高版本 / Android Studio Hedgehog or later
- **Android SDK**: Platform 36 / Android SDK Platform 36
- **Gradle**: 9.4.1 (项目自带 wrapper) / Gradle 9.4.1 (wrapper included)
- **设备**: Wear OS 模拟器或实体设备 / Wear OS emulator or physical device

### 常用构建命令 / Common Build Commands

```bash
# 清理项目 / Clean project
./gradlew clean

# 构建调试版本 / Build debug version
./gradlew assembleDebug

# 构建发布版本 / Build release version
./gradlew assembleRelease

# 安装到设备 / Install to device
./gradlew installDebug

# 运行测试 / Run tests
./gradlew test

# 生成代码覆盖率报告 / Generate code coverage report
./gradlew jacocoTestReport
```

## 贡献指南 / Contributing

欢迎贡献代码、报告问题或提出建议！

Contributions, issues, and suggestions are welcome!

1. Fork 本仓库 / Fork this repository
2. 创建功能分支 / Create a feature branch: `git checkout -b feature/YourFeature`
3. 提交更改 / Commit your changes: `git commit -am 'Add some feature'`
4. 推送到分支 / Push to the branch: `git push origin feature/YourFeature`
5. 提交 Pull Request / Submit a Pull Request

## 许可证 / License

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## 联系方式 / Contact

如有问题或建议，请通过以下方式联系：

For questions or suggestions, please contact via:

- 提交 Issue / Submit an Issue
- 发送邮件至 / Send email to: [your-email@example.com]

## 致谢 / Acknowledgments

- 感谢所有贡献者 / Thanks to all contributors
- 基于 Kotlin 和 Android Jetpack 构建 / Built with Kotlin and Android Jetpack

---

**注意**: 此应用专为 Wear OS 设备设计，可能无法在手机或平板上正常运行。

**Note**: This app is designed specifically for Wear OS devices and may not function properly on phones or tablets.

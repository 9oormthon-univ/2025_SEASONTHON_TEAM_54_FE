## [kakao X groom] Seasonton 54팀 FE

## 🛠️ Development Environment

| 항목 | 내용 |
|------|------|
| **IDE** | Android Studio Meerkat (2024.3.2 Patch1) |
| **Language** | Kotlin (v2.0.21) |
| **Architecture** | MVVM + Clean Architecture |
| **UI Framework** | Jetpack Compose |
| **Module 구조** | Single Activity / Single Module |
| **Navigation** | Jetpack Navigation |
| **Dependency Injection** | Dagger-Hilt |
| **Async** | Coroutine, Flow |
| **Network** | Retrofit, OkHttp |
| **Design System** | Material3 |
| **Build Tooling** | Gradle Version Catalog |
| **Data Handling** | Repository Pattern |


### 💡 기술 선정 이유

#### 1️⃣ MVVM
1. **관심사 분리**
    - UI, 데이터 처리, 비즈니스 로직을 분리해 각 컴포넌트의 책임이 명확해지고, 테스트 및 유지보수 효율이 높아집니다.
2. **View ↔ ViewModel N:1 대응**
    - ViewModel 재사용성이 높아져 보일러플레이트 코드 감소에 효과적입니다.


#### 2️⃣ Repository Pattern
1. **데이터 소스 추상화**
    - 네트워크 및 로컬 DB 등 다양한 데이터 소스를 통합적으로 관리할 수 있어 데이터 접근 로직을 분리할 수 있습니다.
2. **유지보수 및 확장성 향상**
    - 변경 사항을 Repository 레이어에 국한시켜 전체 구조에 영향을 주지 않으므로, 유지보수성이 높습니다.


#### 3️⃣ Dagger-Hilt
1. **통일된 DI 패턴 제공**
    - 일관된 의존성 주입 방식을 통해 협업 시 코드 가독성과 이해도를 높일 수 있습니다.
2. **Android 환경 최적화**
    - Android 컴포넌트에 맞춘 주입 어노테이션을 제공하여 개발 효율성을 높입니다.


## 💡 Convention
#### 🐾 Git Convention
[Git Convention](https://www.notion.so/Git-Convention-25ee818ebf1281baa589ec22b63a63c0)

#### 🪵 Branch Convention
[Branch Convention](https://www.notion.so/Branch-Convention-25ee818ebf12811da760f88a86b29577)

#### 🛠 Coding Convention
[Coding Convention](https://www.notion.so/Coding-Convention-25ee818ebf1281e9a4eec75db111c165)

killall java
cd ..

./gradlew syncDebugLibJars
./gradlew :example:composeApp:resolveIdeDependencies --refresh-dependencies
./gradlew prepareKotlinBuildScriptModel
./gradlew prepareKotlinIdeaImport
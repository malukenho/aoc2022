.DEFAULT_GOAL:=help
.PHONY: help
SHELL:=/bin/bash

day1: ## Eita
	@echo "\n=== D-01"
	@java -javaagent:/Users/malukenho/Library/Application\ Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/223.7571.182/IntelliJ\ IDEA\ CE.app/Contents/lib/idea_rt.jar=62000:/Users/malukenho/Library/Application\ Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/223.7571.182/IntelliJ\ IDEA\ CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/malukenho/github.com/malukenho/aoc-2022/out/production/aoc-2022:/Users/malukenho/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk8/1.7.20/kotlin-stdlib-jdk8-1.7.20.jar:/Users/malukenho/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.7.20/kotlin-stdlib-1.7.20.jar:/Users/malukenho/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-common/1.7.20/kotlin-stdlib-common-1.7.20.jar:/Users/malukenho/.m2/repository/org/jetbrains/annotations/13.0/annotations-13.0.jar:/Users/malukenho/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk7/1.7.20/kotlin-stdlib-jdk7-1.7.20.jar DayOne.MainKt

day2: ## Display this help
	@echo "\n=== D-02"

help: ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z0-9_-]+:.*?##/ { printf "  \033[36m%-10s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)
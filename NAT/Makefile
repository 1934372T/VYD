.DEFAULT_GOAL := help
.PHONY: help
help:	## コマンド一覧表示
	@grep -E '^[[:alnum:]_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: run-dev
run-dev:
	docker-compose -f compose.dev.yml up

.PHONY: build-dev
build-dev:
	docker-compose -f compose.dev.yml up --build
.DEFAULT_GOAL := help
.PHONY: help
help:	## コマンド一覧表示
	@grep -E '^[[:alnum:]_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: run-dev
run-dev:	## 開発環境の設定で実行
	docker-compose -f infra/compose.dev.yml up

.PHONY: build-dev
build-dev:	## 開発環境の設定でビルドして実行
	docker-compose -f infra/compose.dev.yml up --build
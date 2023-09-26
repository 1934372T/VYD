.DEFAULT_GOAL := help
.PHONY: help
help:	## コマンド一覧表示
	@grep -E '^[[:alnum:]_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: run-dev
run-dev:	## 開発環境の設定で実行
	@docker-compose -f infra/compose.dev.yml up

.PHONY: build-dev
build-dev:	## 開発環境の設定でビルドして実行
	@docker-compose -f infra/compose.dev.yml up --build

.PHONY: run-prod
run-prod:
	@echo "\033[0;36m...Building client for student\033[0m"
	@cd client/student && yarn build && cd ../..
	@echo "\033[0;36m...Building client for admin\033[0m"
	@cd client/admin && yarn build && cd ../..
	@echo "\033[0;36m...Building containers\033[0m"
	@docker-compose -f infra/compose.prod.yml up --build
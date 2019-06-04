#!/bin/bash

### FUNÇÕES UTEIS
# Exibe um título no terminal.
# Uso: show_title "Meu título"
show_title() {
	echo ""
	echo ""
	echo "*********************************************************"
	echo "***** $1 *****"
	echo "*********************************************************"
	echo ""
}

# Exibe uma mensagem no terminal com o prefixo configurado em MSG_PREFIX.
# Uso: show_msg "Minha mensagem"
show_msg() {
	echo ""
	echo "$MSG_PREFIX $1"
}

show_help() {
	echo ""
	echo "$MSG_PREFIX $1"
	echo ""
	echo "$MSG_PREFIX AJUDA:"
	echo "$HELP_TEXT"
	echo ""

	exit 1
}

user_confirm() {

	msg=$1
	echo ""
	echo ""
	read -p "$log_prefix $msg (s / n)" -n 1 -r
	echo
	if [[ $REPLY =~ ^[Ss]$ ]]
	then
		return 0
	elif [[ $REPLY =~ ^[Nn]$ ]]
	then
		return 1
	else
		show_msg "Resposta inválida. Responda \"s\" ou \"n\"."

		if confirmacao_usuario "$msg"; then
			return 0
		else
			return 1
		fi
	fi
}

validate_files_exist () {

	for dir in "$@"
	do
		if [[ ! -d "$dir" ]] && [[ ! -f "$dir" ]]; then
			echo ""
			echo ""
			echo "ERRO: O dirétorio/arquivo não existe: $dir"
			echo $2
			echo -e "\n\n"
			exit ${E_BADARGS}
		fi
	done
}

### Inicialização

HELP_TEXT="
	Uso correto: `basename $0` <acao>

	Ações disponíveis:
	      execute    Executa a API
	      generate   Gera um arquivo .jar"

show_title "Build da API de Jogo da velha"

validate_files_exist "src"

### PARÂMETROS
acao=$1

if [[ -z "$acao" ]]; then
	show_help "Argumentos inválidos."
fi

createBuild() {

	show_msg "--> Gerando o arquivo .jar da aplicação."

	./mvnw clean install -DskipTests

}

runApplication() {

	./mvnw spring-boot:run

}

cleanPreviousVersion() {

	show_msg "--> Limpando arquivos da última versão gerada."

	rm -rf target

}

#### Main

case ${acao} in

	execute)

		cleanPreviousVersion
		runApplication

		;;

	generate)

		cleanPreviousVersion
		createBuild

		;;

	*)

		show_help
		exit 1

		;;

esac

exit 0
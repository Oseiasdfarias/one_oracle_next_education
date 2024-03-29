let listaDeNumerosSorteados = [];
let numeroLimite = 10;
let numeroSecreto = gerarNumeroAleatorio();
let tentativas = 1;

function exibirTextoNaTela(tag, texto) {
    let campo = document.querySelector(tag);
    campo.innerHTML = texto;
    audioDescricao(texto);
}

function exibirMensagemInicial() {
    exibirTextoNaTela("h1", "Jogo do número secreto");
    exibirTextoNaTela("p", "Escolha um número de 1 à 10");
}
exibirMensagemInicial();

function verificarChute() {
    let chute = document.querySelector("input").value;
    if (chute == numeroSecreto){
        exibirTextoNaTela("h1", "Acertou!!!");
        let palavraTentativa = tentativas > 1 ? "tentativas": "tentativa";
        let mensagemTenativas = `Você descobriu o número secreto com ${tentativas} ${palavraTentativa}!`;
        exibirTextoNaTela("p", mensagemTenativas);
        document.getElementById("reiniciar").removeAttribute("disabled");

    } else{
        if (chute > numeroSecreto){
            let msg = `O número é menor que ${chute}!`
            exibirTextoNaTela("p", msg);
        } else{
            let msg = `O número é maior que ${chute}!`
            exibirTextoNaTela("p", msg);
        }
        tentativas++;
        limparCampo();
    }
}

function gerarNumeroAleatorio() {
    let numeroEscolhido = parseInt(Math.random() * numeroLimite + 1);
    let quantidadeElementosLista = listaDeNumerosSorteados.length;

    if (quantidadeElementosLista == numeroLimite) {
        listaDeNumerosSorteados = [];
    }
    if (listaDeNumerosSorteados.includes(numeroEscolhido)) {
        return gerarNumeroAleatorio();
    } else {
        listaDeNumerosSorteados.push(numeroEscolhido);
        console.log(listaDeNumerosSorteados);
        return numeroEscolhido;
    }
}

function limparCampo() {
    chute = document.querySelector("input");
    chute.value = "";
}

function reiniciarJogo() {
    numeroSecreto = gerarNumeroAleatorio();
    limparCampo();
    tentativas = 1;
    exibirMensagemInicial();
    document.getElementById("reiniciar").setAttribute("disabled", true);
}

function audioDescricao(texto) {
    responsiveVoice.speak(texto, "Brazilian Portuguese Female",
                          {rate: 1.2});
}
alert("Boas vindas ao jogo dos números secretos!");
let numeroSecreto = 3;
let chute;
let tentativas = 1;

// enquanto o número não for igual ao n.s
while (chute != numeroSecreto) {
    chute = prompt("Escolha um número entre 0 e 10");
    // se chute for igual ao número secreto
    if (chute == numeroSecreto) {
        alert(`Isso ai! Você descobriu o número secreto
               ${numeroSecreto} com ${tentativas} tentativas`);
    } else{
        if (chute > numeroSecreto) {
            alert(`O número secreto é menor que ${chute}`);
        } else {
            alert(`O número secreto é maior que ${chute}`)
        }
        tentativas ++;
    }
}

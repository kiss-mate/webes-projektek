let playerScore = 0, pcScore = 0;
let moves = ['rock', 'paper', 'scissors'];
let keyBeatsValue = {'rock': 'scissors', 'paper': 'rock', 'scissors': "paper"};

function onBodyLoad() {
    document.getElementById('pcMove').style.display = 'none';
}

function updateInfo(response) {
    document.getElementById('response').textContent = response;
    document.getElementById('playerScore').textContent = playerScore;
    document.getElementById('pcScore').textContent = pcScore;
    showAlert();
}

function showAlert() {
    if (playerScore == 10 || pcScore == 10) {
        alert(`${(pcScore > playerScore) ? 'Computer' : 'Player'} won the game!\nClick ok to play again.`)
        window.location.reload();
    }
}

function updateScore(playerMove, pcMove) {
    if (playerMove == pcMove) {
        updateInfo("It's a tie!")
    }
    else if (keyBeatsValue[playerMove] == pcMove) {
        playerScore++;
        updateInfo('Player wins!');
    } else {
        pcScore++;
        updateInfo('Computer wins!');
    }
}

function getRandomMoveFromPC(item) {
    let pcMove = moves[((Math.random() * 10) % 3) | 0];
    document.getElementById('pcMove').src = `${pcMove}.png`;
    document.getElementById('pcMove').style.display = 'block';
    updateScore(item.id, pcMove);
}


function mouseEnter(item) {
    item.style.cursor = 'pointer';
    item.style.border = '3px solid black';
}

function mouseLeave(item) {
    item.style.border = '3px solid grey';
}
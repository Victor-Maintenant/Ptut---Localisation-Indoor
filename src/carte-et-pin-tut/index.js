function posSalle(idSalle) {
  if (idSalle === 1) {
    document.getElementById("marker").style.left = 33 + "%";
    document.getElementById("marker").style.top = 60 + "%";
  }
}

posSalle(1);

function colorIcon(nbPersonne, maxPersonne, idSalle) {
  let pourcentPersonne = nbPersonne / maxPersonne;
  let id = "";
  if (idSalle === 1) {
    id = "nbIcon";
  }
  if (pourcentPersonne >= 0.6) {
    document.getElementById(id).style.background =
      "radial-gradient(#ffd09e, #ffa23f)";
  }
  if (pourcentPersonne >= 0.9) {
    document.getElementById(id).style.background =
      "radial-gradient(#ff9488, #ff2d15)";
  }
}

colorIcon(4, 10, 1);

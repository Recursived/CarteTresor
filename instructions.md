## Important elements: 

1.  File reading :

- Par défaut, toutes les cases de la carte sont des plaines
- Notation matricielle traditionnelle
- Montagnes sont des obstacles infranchissables
- Trésor (peut en exister plusieurs sur une case)
- L’aventurier ignore les mouvements bloquants et poursuit l’exécution de la séquence.
- une ligne débutant par un ‘#’ est un commentaire et doit être ignorée. 
- Si l’aventurier passe par dessus une case Trésor, il ramasse un trésor présent sur la case. Si la case contient 2 trésors, l’aventurier devra quitter la case puis revenir sur celle-ci afin de ramasser le 2ème trésor.
- Il ne peut y avoir qu’un aventurier à la fois sur une même case. Les mouvements des aventuriers sont évalués tour par tour. En cas de conflit entre mouvements sur un même tour, c’est l’ordre d’apparition de l’aventurier dans le fichier qui donne la priorité des mouvements.

 ```
 Formatage des donnnées du fichier

 # {C comme Carte} - {Nb. de case en largeur} - {Nb. de case en hauteur}

 # {M comme Montagne} - {Axe horizontal} - {Axe vertical}

 # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors}

 # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Séquence de mouvement}
 ```

 ## Fin de jeu

 1. File writing:
```
C - 3 - 4
M - 1 - 0
M - 2 - 1
# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}
T - 1 - 3 - 2
# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}
A - Lara - 0 - 3 - S - 3
 ```

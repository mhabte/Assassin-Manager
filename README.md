# Assassin-Manager
The game of assassin is played as follows. You start out with a group of people who want to play the game. For example, let's say that we have five people playing whose names are Joe, Sally, Jim, Carol and Chris. A circular chain of assassination targets is established (what is called the “kill ring” in the sample log of execution). For example, we might decide Joe should stalk Sally, Sally should stalk Jim, Jim should stalk Carol, Carol should stalk Chris and Chris should stalk Joe.

When someone is assassinated, the chain needs to be relinked by “skipping” that person. For example, suppose that Jim is assassinated first (obviously this would have been by Sally). Sally needs a new target, so we give her Jim's target: Carol.

The AssassinManager class allows a user to play under these rules and allows them to get real time info on the status of the game using the methods provided.

To test AssassinManager, download all three files, and run AssassinMain.java while both java files are in the same folder. You'll then be prompted to give a txt file with the names of the participants, type in either the names.txt provided, or a txt file with names of your choice. Afterwards, follow the instructions on the screen to play the game.

NOTE: AssassinMain.java and names.txt are not my work and they're simply provided to provide viewers code and names to use to play the game.

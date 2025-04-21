# Set-Up & How To Start Playing
Download the src folder, as it contains the main program file and all necessary classes. To run the program, open and execute the main file. Currently, the game will only display the side character and its warning messages. The Tetris game board is a work in progress and not yet integrated into the main program. For now, the board exists as a preview within the GameWindow class, primarily for testing and design purposes.

# About The Game
Blah Tetris is a fast-paced twist on the classic Tetris experience. While players try to stack and clear blocks as usual, a pop-up side character will be displayed on the screen. This character issues real-time warnings about upcoming speed increases and surprise obstacles. An AI opponent may randomly drop “junk” blocks or a disruptive “garbage” icon onto the board, simulating competitive pressure through a background thread. Players must respond quickly–either by clicking a key or swiping the garbage off the board with the mouse–to maintain control of their game space. It's Tetris, but with chaos–and a little personality.
## Core Gameplay Loop: Brief explanation of main player actions and feedback loops:
The player’s main objective remains classic to Tetris: rotate and position falling blocks to complete lines and try to keep the blocks to a minimum and not lose by having them overflow. The additional challenge is picking up the speed and adding garbage blocks or obstacles for the player to get rid of while they play as they can disrupt the space in your game board.Throughout the match, the side character gives alerts when gameplay speeds up or when garbage blocks come onto the board. These disruptions must be cleared promptly using a keyboard key or mouse swipe. The game ends after 5 minutes, with the final score calculated based on blocks cleared and how full the board is, awarding 1 to 3 stars.

# Gameplay Mechanics
## Controls:
The game will primarily use keyboard inputs. Arrow keys will control the movement and rotation of falling blocks, just like in traditional Tetris. Additional input includes a specific keyboard key or a mouse swipe to remove the “garbage” blocks or icons dropped.
## Core Mechanics:
The core gameplay revolves around traditional Tetris mechanics: rotating, moving, and placing falling blocks to clear horizontal lines. Added to this is a dynamic side-character system that gives real-time warnings. An AI thread may drop disruptive garbage onto the board, which the player must manually remove using a key press or mouse swipe. This adds a second layer of interaction beyond the standard puzzle gameplay, blending strategy with quick reflexes.
## How the player advances:
The player advances with the time. The match lasts 5 minutes, and each minute introduces a condition that either speeds up the game or adds garbage to the game board.
## Win/Loss Conditions: What determines success or failure?
The player wins if they can survive the full 5-minute round without the blocks reaching the top of the game board. The final score is determined by how many lines the player clears and how clean the board is at the end. A 1-3 star rating system will be used, with more stars awarded for fewer leftover blocks.

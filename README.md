# Demo
https://youtu.be/PCb4pU3VA5o
# Set-Up & How To Start Playing
Download the src folder, as it contains the main program file and all necessary classes. To run the program, open and execute the main file. Press the Start button and the first block will begin falling from the top of the board. 

Use the arrow keys to control the block:

  Left Arrow (←): Move the block left.
  
  Right Arrow (→): Move the block right.
  
  Down Arrow (↓): Move the block down faster.
  
  Up Arrow (↑) (if applicable): Rotate the block.
  
Then when the block reaches the bottom of the board or lands on top of another block, it locks in place. Next, a new block will appear and begin falling. As time passes, unexpected events may occur or new callenges may appear based on a timer. 

# About The Game
Blah Tetris is a fast-paced twist on the classic Tetris experience. While players try to stack and clear blocks as usual, a pop-up side character will be displayed on the screen. This character issues real-time warnings about upcoming speed increases and surprise obstacles. Players must respond quickly to maintain control of their game space. It's Tetris, but with chaos–and a little personality.
## Core Gameplay Loop: Brief explanation of main player actions and feedback loops:
The player’s main objective remains classic to Tetris: rotate and position falling blocks to complete lines and try to keep the blocks to a minimum and not lose by having them overflow. The additional challenge is picking up the speed the player.Throughout the match, the side character gives alerts when gameplay speeds. The game ends after 5 minutes, with the final score calculated based on blocks cleared and how full the board is, awarding 1 to 3 stars.

# Gameplay Mechanics
## Controls:
The game will primarily use keyboard inputs. Arrow keys will control the movement and rotation of falling blocks, just like in traditional Tetris.
## Core Mechanics:
The core gameplay revolves around traditional Tetris mechanics: rotating, moving, and placing falling blocks to clear horizontal lines. Added to this is a dynamic side-character system that gives real-time warnings. This adds a second layer of interaction beyond the standard puzzle gameplay, blending strategy with quick reflexes.
## How the player advances:
The player advances with the time. The match lasts 5 minutes. Survive!
## Win/Loss Conditions: What determines success or failure?
The player wins if they can survive the full 5-minute round without the blocks reaching the top of the game board. The final score is determined by how many lines the player clears and how clean the board is at the end. A 1-3 star rating system will be used, with more stars awarded for fewer leftover blocks.

# Breakdown of OS Concepts Used
*Note - The OS concepts will be updated to represent a lower level than what is currently written*
## Process Creation
PopupThread extends Thread for a character to popup and display warning messages to the player

![image](https://github.com/user-attachments/assets/343b6627-ce7d-46e5-96a8-2880c31715ac)
![image](https://github.com/user-attachments/assets/ceed9bec-2755-4b9a-95ce-dd44f686edbb)
## Threading
Manages the popup character independently from the main UI/game logic.
Threads for game logic, popup, music, etc.

![image](https://github.com/user-attachments/assets/1c328b57-b1d4-444a-ba05-2738d7e24229)
## Signal Handling/Timers
Buttons like Start, Pause, and Exit use listeners to interact with the game state

![image](https://github.com/user-attachments/assets/6965062c-f877-4cb5-8c62-eea39742d6e3)
## Synchronization
The synchronized block makes sure no thread modifies a shared variable or UI component while another thread is using it

![image](https://github.com/user-attachments/assets/ef5aeb69-dddb-406a-a8d0-f7c7f304247f)
## File/Memory Management
Saving the scores and for now using a dummy number

![image](https://github.com/user-attachments/assets/18d7d23d-7a4f-4bea-8e27-082fcf647a09)


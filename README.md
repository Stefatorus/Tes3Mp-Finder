# Tes3Mp-Finder
Tes3Mp finder is a client-side tool that reads the console in order to see where people are hiding.

# How To Install
Installation is pretty simple and straight forward:
- Place the mod in your tes3mp folder, and run it using CMD.
- Input the server connection data (or set the default inside tes3mp-client-default.cfg)
- Done!


# Features
- Direct-connect to servers via their IP and port (can be used to join servers that don't show up in the masterlist)
- Wilderness Map (can be used to visualize where players in the wilderness hide)
- Location Query (find where players are using simple commands)
- In-Game Commands (the mod can fully be controlled in-game without non-admin players being able to notice)
- Fully Safe (the mod doesn't modify the game in any shape or form, and should even be version independent. The mod just works as a log reader and parser, and all features are based on what's outputted there).

# How to Use
Please write the following messages in the in-game chat. The results for the commands can be seen in the cmd program:
/!settarget (player) - set the target to (playername)
/!getlocation (player) - get the location of (playername)

# How to Block (as a server owner)
Fake locations sent to the players' log.
Detect the Tes3Mp-Finder commands /!settarget & /!getlocation

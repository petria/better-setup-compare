# better-setup-compare

Assetto Corsa sim racing game car setup ini file analyzer.

In game, you can save car setup per car and track. Depending on car there are numerous settings
that can be adjusted for example: tyre pressures, gear ratios, wing configuration, camber/toe angles etc.

These settings very much effect how the car handles and individual settings per track needs to be adjusted to get the best performance for that particular track.

This app analyzes saved setup ini files and tells using AI how settings do effect the driving of car.

App can also be used to compare what are the actual differences between different ini files. Like file 1 has wing setting 2 versus file 2 has wing setting 5.


Full Stack web application
- Java Spring Boot back end
- React front end
- Github actions pipeline
- builds on commit
- deploy to docker
- Proxmox virtualization platform running docker server
- Spring AI
- use locally running Ollama instances to execute chat queries- 

TODO:
- Possible to configure multiple ollama hosts which (admin)user chooses what is used for running LLM.
****************************************************************************************************
A simplified flight information system that answers questions of this kind to its users:

• Is there a non-stop flight between two specific airports?
• If not, how many stopovers does the shortest route contain?
• Which flight distance is covered?

****************************************************************************************************

Serialization of a flight route graph as input.txt:

A serialization consists of two areas. The first area describes the nodes of the graph. The second area describes the edges of the graph.

The first area contains two or more lines in any order.
Each line describes an airport (node) and is structured as follows:

  <IATA code> <airport name>

The second area contains one or more rows in any order. Each line describes one
Flight route (edge) and is structured as follows:

  <Start Airport> <Destination> <distance>
  
****************************************************************************************************

Interactive user interface:

- airport command

The airport command outputs the name of a specific airport to the console.

Input format: airport <airport> , <airport> is an IATA code

- connected command

Input format: connected <start airport>; <destination airport>

- distance command

The distance command gives the distance between a departure airport and a destination airport

Input format: distance <departure airport>; <destination airport>

- routes command

The routes command returns to the console how many routes from a particular departure airport to one
certain destination airports containing exactly z intermediate stops.

Input format: routes <airport of departure>; <destination airport>; <stopovers>

- shortest command

The shortest command gives the distance of the shortest path between a particular departure airport and
to a particular destination from the console.

Input format: shortest <departure airport>; <destination airport>

- quit command

This command terminates the program.

****************************************************************************************************
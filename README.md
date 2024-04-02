# CTASearchAgent
- Modifying the AIMA libraries files and Joseph Phillips parser to map the CTA and customize my search agent for my specific needs
- How it works:
  - The unoptimized search performs a single IDDFS to find a path between two stops, making it inefficient in memory usage.
  - The optimized search performs multiple IDDFS for every supernode between two stops, making it efficient in memory usage.
  
- Credit to AIMA for their Java library
  > https://github.com/aimacode/aima-java
- Credit to Joseph Phillips for the graph parser and streaming files

# What To Install
- Eclipse
- JDK 17

# How to use
- In Eclipse, git clone this repository.
- You can either run the optimized IDDFS search or the unoptimized IDDFS search
- Use the provided text file paths for the searches
- The names of stops are in the ctaTrain.txt file (the nodes)


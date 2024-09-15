# Detection of Motifs in Artificial Genetic Sequences

## Description

This repository contains the source code of a project developed for the first workshop of the Systems Analysis course, focused on the detection of motifs in artificial genetic sequences. The program, implemented in Java, generates random DNA sequences, calculates their entropy, and searches for repetitive patterns (motifs) of a specific size.

## Features

* **Sequence generation:** Creates random DNA sequences of configurable length, composed of the bases A, C, G, and T.
* **Entropy calculation:** Measures the randomness of each sequence using Shannon entropy.
* **Sequence filtering:** Selects sequences with an entropy above the average to focus on more informative regions.
* **Motif detection:** Identifies patterns of subsequences that are repeated most frequently in the filtered sequences.

## Project Structure

* **Workshop.java:** Contains the main implementation of the algorithm, including sequence generation, entropy calculation, filtering, and motif search.
* **secuencias_todas.txt:** File that stores all generated sequences.
* **secuencias_filtradas.txt:** File that contains the sequences selected after entropy filtering.
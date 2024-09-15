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

## Configurable Parameters

The program's parameters can be modified directly in the source code, such as the number of sequences, the size of the sequences, the probabilities of each base, and the size of the motif to search for.

## Results

The program generates an output in the console that includes:

* The most frequent sequence found.
* The frequency of occurrence of this motif.
* The average entropy of the sequences.
* The execution time of the algorithm.

## Considerations and Future Improvements

* **Complexity:** The algorithm has a time complexity that depends on the size of the sequences and the length of the motif.
* **Optimizations:** Dynamic programming techniques could be explored to improve the efficiency of motif searching.
* **Extensibility:** The code can be adapted to analyze other types of biological sequences or to incorporate different sequence generation models.
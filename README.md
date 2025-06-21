# 🍹 Cocktailpedia

![Language](https://img.shields.io/badge/language-Kotlin-blue.svg)
![Platform](https://img.shields.io/badge/platform-Android-brightgreen.svg)

Application Android native développée en Kotlin avec Jetpack Compose. Elle permet de parcourir, rechercher et découvrir des recettes de cocktails en utilisant l'API [TheCocktailDB](https://www.thecocktaildb.com/). Ce projet a été réalisé dans un cadre d'apprentissage du développement Android.

## 📱 Aperçus

| Écran d'accueil | Écran de recherche | Écran de détail |
| :-------------: | :-------------: | :-------------: |
| <img src="https://github.com/user-attachments/assets/3fe1e3c2-1e71-43d9-b2c7-53a835b57a8d" alt="Ecran d'accueil" width="250"> | <img src="https://github.com/user-attachments/assets/058330fa-f0f8-48fd-8aea-cd670f0eaeca" alt="Ecran de recherche" width="250">) | <img src="https://github.com/user-attachments/assets/00c0997a-5416-4b76-8bba-5b74153c29b6" alt="Ecran de Details" width="250"> |

## ✨ Fonctionnalités

-   **Accueil** : Affiche un cocktail aléatoire au lancement de l'application.
-   **Recherche** : Permet de rechercher un cocktail par son nom.
-   **Parcourir** : Offre la possibilité de naviguer dans les cocktails par catégorie.
-   **Détails Complets** : Affiche une vue détaillée pour chaque cocktail, incluant :
    -   Image
    -   Catégorie, type de verre, type d'alcool
    -   Liste complète des ingrédients et des mesures
    -   Instructions de préparation
-   **Interface Multilingue** : L'interface de l'application est disponible en Français et en Anglais.
-   **Contenu Multilingue** : Affiche les instructions de préparation dans la langue de l'appareil (si disponible via l'API).

## 🛠️ Stack Technique

-   **Langage** : [Kotlin](https://kotlinlang.org/)
-   **UI** : [Jetpack Compose](https://developer.android.com/jetpack/compose).
-   **Architecture** :  **MVVM** (Model-View-ViewModel).
-   **Asynchronisme** : [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html).
-   **Navigation** : [Navigation for Compose](https://developer.android.com/jetpack/compose/navigation)s.
-   **Réseau** : [OkHttp](https://square.github.io/okhttp/).
-   **Parsing JSON** : [Gson](https://github.com/google/gson).
-   **Chargement d'images** : [Coil](https://coil-kt.github.io/coil/).

## 🚀 Installation

Pour lancer le projet localement, suivez ces étapes :

1.  Clonez le dépôt sur votre machine :
    ```bash
    git clone [https://github.com/VotreNomUtilisateur/NomDuProjet.git](https://github.com/VotreNomUtilisateur/NomDuProjet.git)
    ```
2.  Ouvrez le projet avec la dernière version stable d'Android Studio.
3.  Laissez Gradle synchroniser et télécharger toutes les dépendances.
4.  Lancez l'application sur un émulateur ou un appareil physique.

## 🌐 API

Ce projet utilise l'API gratuite et publique [TheCocktailDB](https://www.thecocktaildb.com/api.php). Un grand merci à eux pour la mise à disposition de ces données.

## 👤 Auteur

**Antoine Ferron**

-   GitHub : [Antoine Ferron](https://github.com/Antoine-Ferron)
-   LinkedIn : [Antoine Ferron](https://www.linkedin.com/in/antoine-ferron6/)

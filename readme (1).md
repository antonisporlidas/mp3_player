# 🎵 Modern JavaFX MP3 Player

Ένας σύγχρονος, native αναπαραγωγέας μουσικής (MP3 Player) υλοποιημένος σε **Java** και **JavaFX**, με dark-neon αισθητική (CSS glow effects), σύστημα αυθεντικοποίησης (Login), διαχείριση λίστας αγαπημένων κομματιών και πλήρη έλεγχο αναπαραγωγής.

---

## 📸 Χαρακτηριστικά (Features)

* **🔐 Σύστημα Εισόδου (Login Gate):**
  * Προστασία της εφαρμογής με παράθυρο αυθεντικοποίησης (`Login.fxml`).
  * Έλεγχος διαπιστευτηρίων πριν την πρόσβαση στο κύριο περιβάλλον.
* **🎧 Πλήρης Έλεγχος Αναπαραγωγής Ήχου:**
  * Play, Pause, Reset, Next, Previous κομματιών.
  * Δυναμική φόρτωση αρχείων `.mp3` από τοπικό φάκελο (`music/`).
  * Ρύθμιση ταχύτητας αναπαραγωγής (Playback Speed: 25% έως 200%).
  * Ρύθμιση έντασης ήχου (Volume Slider).
  * Μπάρα προόδου τραγουδιού σε πραγματικό χρόνο (`ProgressBar` με `TimerTask`).
* **⭐ Διαχείριση Αγαπημένων (Favourites):**
  * Προσθήκη / αφαίρεση κομματιών από τα αγαπημένα.
  * Ξεχωριστό παράθυρο (`Favourites.fxml`) που προβάλλει τη συγκεντρωτική λίστα αγαπημένων μέσω κοινόχρηστου `ObservableList`.
* **🎨 Σύγχρονος Σχεδιασμός & Visual FX:**
  * Σκούρο θέμα (Dark Theme) με κυανές/neon πινελιές (`#4fc3f7`).
  * Dynamic "Breathing" Glow animation στον τίτλο μέσω JavaFX `Timeline` & `DropShadow`.
  * Προσαρμοσμένα CSS κουμπιά χαπιού (Pill buttons), styled scrollbars και hover εφέ.

---

## 🛠️ Τεχνολογίες & Βιβλιοθήκες

| Στοιχείο | Τεχνολογία / Εργαλείο |
| :--- | :--- |
| **Γλώσσα Προγραμματισμού** | Java (JDK 17+) |
| **UI Framework** | JavaFX (v21) |
| **Αρχιτεκτονική UI** | FXML (Scene Builder compatible) & CSS |
| **Media Engine** | `javafx.scene.media.Media` / `MediaPlayer` |
| **IDE** | Eclipse IDE (με e(fx)clipse plugin) |

---

## 🔑 Διαπιστευτήρια Σύνδεσης (Default Credentials)

Για να συνδεθείτε στην εφαρμογή μέσω του παραθύρου εισόδου:

* **Όνομα Χρήστη (Username):** `user`
* **Κωδικός (Password):** `pass`

---

## 📂 Δομή Έργου (Project Structure)

```text
src/
└── application/
    ├── Main.java                 # Κλάση εκκίνησης (φορτώνει αρχικά το Login.fxml)
    ├── LoginController.java      # Έλεγχος ταυτότητας χρήστη
    ├── SampleController.java     # Κύριος ελεγκτής του Player (ήχος, timers, animations)
    ├── FavouritesController.java # Ελεγκτής του παραθύρου αγαπημένων
    ├── Login.fxml                # Διεπαφή οθόνης σύνδεσης
    ├── Sample.fxml               # Κύρια διεπαφή αναπαραγωγής
    ├── Favourites.fxml           # Διεπαφή παραθύρου αγαπημένων
    └── application.css           # Styling, χρώματα, glow εφέ και γραμματοσειρές

music/                            # Φάκελος όπου τοποθετούνται τα αρχεία .mp3
```

---

## 🚀 Οδηγίες Εγκατάστασης & Εκτέλεσης

### Προαπαιτούμενα
1. Εγκατεστημένο **JDK 17** ή νεότερο.
2. Το **JavaFX SDK** (έκδοση αντίστοιχη του JDK).
3. **Eclipse IDE** με ρυθμισμένη τη JavaFX User Library (ή αντίστοιχο build tool όπως Maven/Gradle).

### Βήματα στο Eclipse
1. Κλωνοποιήστε το repository στον υπολογιστή σας:
   ```bash
   git clone https://github.com/<username>/mp3_player.git
   ```
2. Στο Eclipse: **File** -> **Import...** -> **General** -> **Existing Projects into Workspace**.
3. Τοποθετήστε τα αρχεία τραγουδιών σας (`.mp3`) μέσα στον φάκελο `music/` στη ρίζα του project.
4. Βεβαιωθείτε ότι έχουν προστεθεί τα VM arguments για το JavaFX στο Run Configuration (εφόσον απαιτείται):
   ```bash
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media
   ```
5. Κάντε δεξί κλικ στο `Main.java` -> **Run As** -> **Java Application**.
# Note Flow

This is a sample application to demonstrate how to use Kotlin Flow and Room together in MVVM architecture.

In the app, you can create notes, display, edit and update them. If you edit a note you also can display edit history. If you provide an image url while creating the note, it will be displayed immediately.

When the app opens, previously created notes to be listed if there is any record in the db. In ```NotesViewModel``` there is a flow named ```notesFlow``` to observe if there is any changing in the db. When a new note created, edited or deleted note list will be updated immediately by flow. 

## Code Quality
Run ```./gradlew checkCode``` to see lint and detekt warnings, errors. This task also runs all unit tests. If you want to run just unit tests then run ```./gradlew test```.

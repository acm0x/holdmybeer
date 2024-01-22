# Hold My beer
This application is designed to showcase a different beer kinds for someone to hold.


## App Architecture
The app would have following important considerations in mind: 
1. App is a prototype only, so just limited architecture will be introduced:
 * View layer driven mainly by Jetpack Compose and discreet navigation that transitions between 2 screens within same activity based on the State (Loading, ShowList, ShowDetails)
 * ViewModel layer comprises of a single ViewModel class that asynchronously communicates with Data layer retrieves it and notifies about the State change to a View layer triggering UI update.
 * Domain layer, the simplistic one that only declares Beer object and BeerRepository interface(no UseCases!) 
 * Data layer - the async data retrieval and propagating to other layers.

### Assumptions
* Even though the implementation is primitive, the Data mapping still make sense as it eliminates the risk of unintended data feed changes. (That would render app completely useless)
* The DI will not be used to control object creation, given the fact that we have only a single view model, it would instantiate data repository at the creation time 
* No data expected to be stored on the device
* No package structure is introduced to showcase feature split (only the lightweight presentation-model-data)
* As there's no "Refresh" button, the data will be fetched every time the app is loaded, but not re-fetched until the app is restarted
* Scroll state is not persisted

## Testing
No thorough testing intended, but critical check will be done: is viewModel receives correct data and passes the right state to View and updates view once

**MainViewModelTest** attempts to verify the screen state for all available scenarios with mocking the Repository. 



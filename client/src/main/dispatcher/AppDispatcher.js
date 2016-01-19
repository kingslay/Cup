var Dispatcher = require('flux').Dispatcher;
var AppDispatcher = new Dispatcher();

AppDispatcher.register(function (action) {
  switch(action.actionType) {
    default:
      // no op
  }
})

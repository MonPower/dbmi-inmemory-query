library(shiny)
library(RCurl)
library(jsonlite)
library(shinyvalidinp)
shinyServer(function(input, output) {
     
     idOut <- eventReactive(input$searchButton, {
        queryInput <- URLencode(input$id)
        historical <- getURL(paste0("http://localhost:8080/gemfire-api/v1/queries/adhoc?q=select%20*%20%20from%20/regionA%20where%20", queryInput))
        historicalSet <- fromJSON(historical)
		historicalSet
     })
     output$mytable <- renderDataTable({
		idOut()
     })
  }
)

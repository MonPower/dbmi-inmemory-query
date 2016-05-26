library(shiny)
shinyUI(fluidPage(
  titlePanel("censusVis"),
  
  sidebarLayout(
    sidebarPanel(
      helpText("In memory database search."),
      
      textInput("id",
	label = "Search Constrains(eg. id = 102 and firstName = 'Sachin')"
	),
      actionButton("searchButton", label = "search")
    ),
    
    mainPanel(
		dataTableOutput('mytable')
    )
  )
))

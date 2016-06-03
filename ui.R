library(shiny)
shinyUI(fluidPage(
  titlePanel("mimiciii"),
  
  sidebarLayout(
    sidebarPanel(
      helpText("In memory database search."),
      selectInput('in1', 'Tables', c("ADMISSIONS" = "regionA", "CALLOUT" = "regionB", "CAREGIVERS" = "regionC", "CHARTEVENTS" = "regionD", "CPTEVENTS" = "regionE", "D_CPT" = "regionF"), selectize=FALSE),
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

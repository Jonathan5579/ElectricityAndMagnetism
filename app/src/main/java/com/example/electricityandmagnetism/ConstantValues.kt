package com.example.electricityandmagnetism

import androidx.compose.runtime.Composable

/**Color constants*/
const val TopBarBackgroundColor = 0xFF0E1B4B//0xFF224997
const val TOP_BAR_BACKGROUND_COLOR_DARK = 0xFF0E1B4B
const val TOP_BAR_BACKGROUND_COLOR_LIGHT = 0xFF224997
const val TopBarTextColor = 0xFFFFFFFF//0xFF696969
const val textTitleColor = 0xFF000000
const val textSubTitleColor = 0xFF5A5A5A

/**Fundamentals Images Ids*/
const val RDrawableElectricFillerImage = -1
const val RDrawableElectricCharges = 0
const val RDrawableElectricField = 1
const val RDrawableElectrostaticPotentialEnergyIcon = 2
const val RDrawableElectricPotentialIcon = 3
const val RDrawableEquipotentialSurfacesIcon = 4
const val RDrawableElectricFieldAndEquipotential = 5
const val RDrawableDistributionOfCharges = 6

/**Funciones de apoyo más que de despliegue de UI*/
@Composable
fun getDrawableFromFundamentalCard(
    imageid: Int
): Int{
    return when (imageid){
        RDrawableElectricFillerImage -> R.drawable.electric_filler_image
        RDrawableElectricCharges -> R.drawable.electric_charges_icon
        RDrawableElectricField -> R.drawable.electric_field_icon
        RDrawableElectrostaticPotentialEnergyIcon -> R.drawable.electrostatic_potential_energy_icon
        RDrawableElectricPotentialIcon -> R.drawable.electric_potential_icon
        RDrawableEquipotentialSurfacesIcon -> R.drawable.equipotential_surfaces_icon
        RDrawableElectricFieldAndEquipotential -> R.drawable.e_field_to_equipotential_icon
        RDrawableDistributionOfCharges -> R.drawable.distribution_of_charge_icon
        else -> R.drawable.imageplaceholder
    }
}

//const val DEFAULT_RAW_JSON = "{\"data\":[{\"content\":\"Proton+, electron-. When close, feel a force in the radial ( r̂ ) direction.\\nF ∝ qq/r²\",\"id\":0,\"title\":\"Electric charges ( q )\"},{\"content\":\"A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point.\\nE ∝ F/q\",\"id\":1,\"title\":\"Electric Field ( E )\"},{\"content\":\"Start with a charge, then bring another charge from infinity to a distance r, for that you\\u0027ll have to overcome the electric force. µ is the energy that took to assemble that charge together. \\nµ ∝ q/r\\nGenerally µ \\u003d ∫F • dr̂\\nElectric fields are conservative, which means that the energy to assemble charges is path independent, calculate it tipically from the straight line that join the charges.\",\"id\":2,\"title\":\"Electrostatic Potential Energy ( µ )\"},{\"content\":\"Amount of energy to approach a charge at a distance r per unit charge, essentially µ/q where q is the new charge.\\nV ∝ q/r\\nGenerally V \\u003d ∫(F/q) • dr̂ \\u003d ∫E • dr̂\",\"id\":3,\"title\":\"Electric Potential ( V )\"},{\"content\":\"For a given charge assembly in space, they produce surfaces that are at the same potential. Equipotential surfaces cannot intersect, that is a violation to conservation of energy.\\nCharges can move alogn these surfaces without doing work.\",\"id\":4,\"title\":\"Equipotential surfaces\"},{\"content\":\"Electric field lines will always go trough equipotential surfaces at a perpendicular angle. If not, its a contradiction to conservation of energy.\\nIn cartesian coordinates they\\u0027re related by the equation \\nE \\u003d -∇V\\nWith spherical coordinates is simply minus the derivative of the electric potential.\\nBy the definition of the gradient (points in the direction of maximum increse of the scalar function), you can see that the electric field points towards the value of minor potential\",\"id\":5,\"title\":\"Electric Field and equipotential surfaces\"},{\"content\":\"The surface is an equipotential, but the distribution of charges will only be uniformly distributed if the conductor is a sphere, for any other shape the surface charge density will be higher where the curvature is also high.\\n\\nσ ∝ curvature\\nSurface charge density.\",\"id\":6,\"title\":\"Distribution of charge in a conductor\"}]}\n"
//const val DEFAULT_RAW_JSON = "{\"data\":[{\"content\":\"Proton+, electron-. When close, feel a force in the radial ( r̂ ) direction.\\nF ∝ qq/r²\",\"id\":0,\"title\":\"Electric charges ( q )\"},{\"content\":\"A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point.\\nE ∝ F/q\",\"id\":1,\"title\":\"Electric Field ( E )\"},{\"content\":\"Start with a charge, then bring another charge from infinity to a distance r, for that you\\u0027ll have to overcome the electric force. µ is the energy that took to assemble that charge together. \\nµ ∝ q/r\\nGenerally µ \\u003d ∫F • dr̂\\nElectric fields are conservative, which means that the energy to assemble charges is path independent, calculate it tipically from the straight line that join the charges.\",\"id\":2,\"title\":\"Electrostatic Potential Energy ( µ )\"},{\"content\":\"Amount of energy to approach a charge at a distance r per unit charge, essentially µ/q where q is the new charge.\\nV ∝ q/r\\nGenerally V \\u003d ∫(F/q) • dr̂ \\u003d ∫E • dr̂\",\"id\":3,\"title\":\"Electric Potential ( V )\"},{\"content\":\"For a given charge assembly in space, they produce surfaces that are at the same potential. Equipotential surfaces cannot intersect, that is a violation to conservation of energy.\\nCharges can move alogn these surfaces without doing work.\",\"id\":4,\"title\":\"Equipotential surfaces\"},{\"content\":\"Electric field lines will always go trough equipotential surfaces at a perpendicular angle. If not, its a contradiction to conservation of energy.\\nIn cartesian coordinates they\\u0027re related by the equation \\nE \\u003d -∇V\\nWith spherical coordinates is simply minus the derivative of the electric potential.\\nBy the definition of the gradient (points in the direction of maximum increse of the scalar function), you can see that the electric field points towards the value of minor potential\",\"id\":5,\"title\":\"Electric Field and equipotential surfaces\"},{\"content\":\"The surface is an equipotential, but the distribution of charges will only be uniformly distributed if the conductor is a sphere, for any other shape the surface charge density will be higher where the curvature is also high.\\n\\nσ ∝ curvature\\nSurface charge density.\",\"id\":6,\"title\":\"Distribution of charge in a conductor\"},{\"content\":\"Content test\",\"id\":7,\"title\":\"Title test\"}]}"
const val DEFAULT_RAW_JSON = "{\"data\":[{\"content\":\"Proton+, electron-. When close, feel a force in the radial ( r̂ ) direction..\\nF ∝ qq/r²\",\"id\":0,\"itemOrdering\":0,\"title\":\"Electric charges ( q )\"},{\"content\":\"A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point.\\nE ∝ F/q\",\"id\":1,\"itemOrdering\":1,\"title\":\"Electric Field ( E )\"},{\"content\":\"Start with a charge, then bring another charge from infinity to a distance r, for that you\\u0027ll have to overcome the electric force. µ is the energy that took to assemble that charge together. \\nµ ∝ q/r\\nGenerally µ \\u003d ∫F • dr̂\\nElectric fields are conservative, which means that the energy to assemble charges is path independent, calculate it tipically from the straight line that join the charges.\",\"id\":2,\"itemOrdering\":2,\"title\":\"Electrostatic Potential Energy ( µ )\"},{\"content\":\"Amount of energy to approach a charge at a distance r per unit charge, essentially µ/q where q is the new charge.\\nV ∝ q/r\\nGenerally V \\u003d ∫(F/q) • dr̂ \\u003d ∫E • dr̂\",\"id\":3,\"itemOrdering\":3,\"title\":\"Electric Potential ( V )\"},{\"content\":\"For a given charge assembly in space, they produce surfaces that are at the same potential. Equipotential surfaces cannot intersect, that is a violation to conservation of energy.\\nCharges can move alogn these surfaces without doing work.\",\"id\":4,\"itemOrdering\":4,\"title\":\"Equipotential surfaces\"},{\"content\":\"Electric field lines will always go trough equipotential surfaces at a perpendicular angle. If not, its a contradiction to conservation of energy.\\nIn cartesian coordinates they\\u0027re related by the equation \\nE \\u003d -∇V\\nWith spherical coordinates is simply minus the derivative of the electric potential.\\nBy the definition of the gradient (points in the direction of maximum increse of the scalar function), you can see that the electric field points towards the value of minor potential\",\"id\":5,\"itemOrdering\":5,\"title\":\"Electric Field and equipotential surfaces\"},{\"content\":\"The surface is an equipotential, but the distribution of charges will only be uniformly distributed if the conductor is a sphere, for any other shape the surface charge density will be higher where the curvature is also high.\\n\\nσ ∝ curvature\\nSurface charge density.\",\"id\":6,\"itemOrdering\":6,\"title\":\"Distribution of charge in a conductor\"},{\"content\":\"Content\",\"id\":7,\"itemOrdering\":7,\"title\":\"Gaussian Flux\"}]}"
/*
const val ButtonBackgroundColor = 0xFFFFD966//0xFFFFCA28
const val ClearUserBankColor = 0xC9FF8686
const val BottomBarBackgroundColor = 0xFFFFFAFA
//candidatos = 0xFFFF8686, 0xFFB2FF8F
val AnswerColorBackgrounds = listOf(0xFFFFEBEB, 0xFFECFFE5, 0xFF37FF00,0xFF37FF00,0xFF37FF00,0xFF37FF00 ) //0xFF37FF00Is the default in case the options exceed 2 reactives for now
val asd = Color(0xFF000000)
//0xFFFFEBEB, 0xFFEBFFE2,
const val MAX_INTENTS_TO_REQUEST_DATA = 3
const val GUID = "No sé qué es esto"
*/
package com.example.electricityandmagnetism

fun buildDefaultFundamentals(): MutableList<FundamentalCard> {
    val fundamentalCards: MutableList<FundamentalCard> = mutableListOf()

    var cardId = 0

    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Electric charges ( q )",
            imageId = RDrawableElectricCharges,
            content = "Proton+ and electron-, when close, feel a force in the radial ( r̂ ) direction, \nF ∝ qq/r²"
        )
    )

    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Electric Field ( E )",
            imageId = RDrawableElectricField,
            content = "A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point, \nE ∝ F/q"
        )
    )

    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Electrostatic Potential Energy ( µ )",
            imageId = RDrawableElectrostaticPotentialEnergyIcon,
            content = "Start with a charge, then bring another charge from infinity to a distance r. µ is the energy that took to assemble that charge together. " +
                    "\nµ ∝ q/r" +
                    "\nGenerally µ = ∫F • dr̂" +
                    "\nElectric field are conservative, which means that the energy to assemble charges is path independent, calculate it tipically from the straight line that join the charges" //Dot Symbols • ◦ • ‣ ⁃
        )
    )

    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Electric Potential ( V )",
            imageId = RDrawableElectricPotentialIcon,
            content = "Amount of energy to approach a charge at a distance r per unit charge " +
                    "\nV ∝ q/r" +
                    "\nGenerally V = ∫(F/q) • dr̂ = ∫E • dr̂"
        )
    )

    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Equipotential surfaces",
            imageId = RDrawableEquipotentialSurfacesIcon,
            content = "for a given charge assembly in space, they produce surfaces that are at the same potential. Equipotential surfaces cannot intersect, that is a violation to conservation of energy." +
                    "\nCharges can move alogn these surfaces without requiring energy."
        )
    )

    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Electric Field and equipotential surfaces",
            imageId = RDrawableElectricFieldAndEquipotential,
            content = "Electric field lines will always go trough equipotential surfaces at a perpendicular angle. If not its a contradictory to conservation of energy." +
                    "\nIn cartesian coordinates they're related by the equation " +
                    "\nE = -∇V" +
                    "\nWith spherical coordinates is simply minus the derivative" +
                    "\nBy the definition of the gradient (points in the direction of maximum increse of function V), you can see that the electric field point towards the value of minor potential"
        )
    )

    cardId += 1
    fundamentalCards.add(
        FundamentalCard(
            id = cardId,
            title = "Distribution of charge in a conductor",
            imageId = RDrawableDistributionOfCharges,
            content = "The surface is an equipotential, but the distribution of charges will only be uniformly distributed if the conductor is a sphere, for any other shape the surface charge density will be higher where the curvature is also high\n" +
                    "\nσ ∝ curvature"
        )
    )

    return fundamentalCards
}
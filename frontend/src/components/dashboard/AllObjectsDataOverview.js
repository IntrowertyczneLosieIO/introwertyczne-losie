import React from "react";
import CurrentDataOverview from "./CurrentDataOverview";

class AllObjectsDataOverview extends React.Component {

    render() {
        let objectsList = this.props.objects.map((object) => {
            return <CurrentDataOverview name={object.objectsSetName} more={object.expandData} addNew={object.addNewObject} key={object.key}/>
        })

        return (
            <div className={"bg-light border border-secondary rounded pl-3 pr-3"}>
                <h3 className={"mt-3 mb-4 text-secondary"}>Informacje o biężacej rekrutacji</h3>
                {objectsList}
            </div>
        )
    };
}

export default AllObjectsDataOverview;
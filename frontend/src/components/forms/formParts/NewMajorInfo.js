import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class NewMajorInfo extends React.Component {

    constructor(props) {
        super(props);
        // this.facultyRef = React.createRef();
        // this.modeRef = React.createRef();
        // this.typeRef = React.createRef();
        // this.mixedRef = React.createRef();
    }

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        let componentList = [];
        for (let i=0; i<this.props.options; i++) {
            componentList.push(<option key={i+1}>{i+1}</option>);
        }

        return (
            <>
                <h4 className={"mt-4 text-center mb-3"}>Informacje o kierunku</h4>
                <Form.Row>
                    <FormGroup as={Col} controlId={"major"}>
                        <Form.Label>Kierunek</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"faculty"}>
                        <Form.Label>Wydział</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            {componentList}
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"modeOfStudy"}>
                        <Form.Label>Rodzaj</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            <option>stacjonarne</option>
                            <option>niestacjonarne</option>
                        </Form.Control>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"type"}>
                        <Form.Label>Typ egzaminu</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"}/>*/}
                            <option value={"pisemny"}>pisemny</option>
                            <option>ustny</option>
                        </Form.Control>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"mixed"}>
                        <Form.Label>Kierunek łączony</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"}/>*/}
                            <option>Tak</option>
                            <option>Nie</option>
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
            </>
        );
    }
}

export default NewMajorInfo;
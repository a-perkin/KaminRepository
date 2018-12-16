
/**
 * Created by aleks on 01.12.2018.
 */
class MyComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            Materials: []
        };
    }
    componentDidMount() {
        fetch("http://localhost:8082/controller/services")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        Materials: result.Materials
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }
    render() {
        const { error, isLoaded, Materials } = this.state;
        if (error) {
            return "Error: {error.message}";
        } else if (!isLoaded) {
            return "Loading...";
        } else {
            return (
                React.createElement(
                    "ul",
                    null,
                    Materials.map(function (material) {
                        return React.createElement(
                            "li",
                            { key: material.name },
                            material.name,
                            " ",
                            material.price
                        );
                    })
                )
            );
        }
    }

}
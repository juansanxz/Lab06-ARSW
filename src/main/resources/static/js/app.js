var blueprintsModule = (function()  {
    var author;
    var bps;

    return {
      setAuthor: function(newAuthor) {
        author = newAuthor;
      },
      getAuthor: function() {
        return author;
      },
      setBpsByAuthor: function(authorToFind) {
        bps = apimock.getBlueprintsByAuthor(authorToFind, function(blueprints) {
            var transformedBlueprints = blueprints.map(function(bp) {
                return {
                    name: bp.name,
                    numPoints: bp.points.length
                }
            });
            console.log(transformedBlueprints);
            return transformedBlueprints;
        });
        console.log(bps);
        var table = $("#blueprintTable");
        var rowsToAdd= bps.map(function(bp) {
          var newRow = $("<tr>");
          newRow.append("<td>" + bp.name + "</td>");
          newRow.append("<td>" + bp.numPoints + "</td>");
          newRow.append("<td><button>Open</button></td>");
          return newRow;
        });

        table.append(rowsToAdd);

        },
        getBps: function() {
            return bps;
        }
     }
})();

blueprintsModule.setAuthor("johnconnor");
console.log(blueprintsModule.getAuthor());
blueprintsModule.setBpsByAuthor("johnconnor");
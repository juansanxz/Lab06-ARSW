var blueprintsModule = (function()  {
    var author;
    var bps;

    return {
      setAuthor: function(newAuthor) {
        author = newAuthor;
        blueprintsModule.setBpsByAuthor(author);
        $("#authorNameText").text("Name of Author: " + author);
      },

      getAuthor: function() {
        return author;
      },

      setBpsByAuthor: function(authorToFind) {
        apimock.getBlueprintsByAuthor(authorToFind, function(blueprints) {
            var transformedBlueprints = blueprints.map(function(bp) {
                return {
                    name: bp.name,
                    numPoints: bp.points.length
                }
            });
            bps = transformedBlueprints;
        });

        var table = $("#blueprintTable");
        var rowsToAdd= bps.map(function(bp) {
          var newRow = $("<tr>");
          newRow.append("<td>" + bp.name + "</td>");
          newRow.append("<td>" + bp.numPoints + "</td>");
          newRow.append("<td><button>Open</button></td>");
          newRow.append("</tr>");
          return newRow;
        });
        $("#blueprintTable tr:not(:first-child)").remove();
        table.append(rowsToAdd);
        var pointsNumber = bps.reduce(function(sum, bp) {
            return sum + bp.numPoints;
        },0);

        $("#totalUserPoints").text("Total user points: " + pointsNumber);

        },

        getBps: function() {
            return bps;
        }
     }
})();

//blueprintsModule.setAuthor("johnconnor");
//blueprintsModule.setBpsByAuthor("johnconnor");